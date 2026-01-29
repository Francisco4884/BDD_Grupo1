package Server.Controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import BusinessLogic.vCuenta_BL;
import DataAccess.DTO.vCuenta_DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Controlador para manejar operaciones CRUD de Cuentas
 */
public class CuentaController implements HttpHandler {
    private vCuenta_BL cuentaBL = new vCuenta_BL();
    private Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Habilitar CORS
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        
        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        String method = exchange.getRequestMethod();
        
        try {
            switch (method) {
                case "GET":
                    handleGet(exchange);
                    break;
                case "POST":
                    handlePost(exchange);
                    break;
                case "PUT":
                    handlePut(exchange);
                    break;
                case "DELETE":
                    handleDelete(exchange);
                    break;
                default:
                    exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, "Error en el servidor: " + e.getMessage());
        }
    }

    /**
     * GET - Obtener todas las cuentas o una específica
     */
    private void handleGet(HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.contains("id=")) {
            // Buscar por ID
            int id = Integer.parseInt(query.split("id=")[1]);
            vCuenta_DTO cuenta = cuentaBL.getBy(id);
            
            if (cuenta != null) {
                sendJsonResponse(exchange, 200, cuenta);
            } else {
                JsonObject response = new JsonObject();
                response.addProperty("success", false);
                response.addProperty("message", "Cuenta no encontrada");
                sendJsonResponse(exchange, 404, response);
            }
        } else {
            // Obtener todas las cuentas
            List<vCuenta_DTO> cuentas = cuentaBL.getAll();
            sendJsonResponse(exchange, 200, cuentas);
        }
    }

    /**
     * POST - Crear una nueva cuenta
     */
    private void handlePost(HttpExchange exchange) throws Exception {
        String requestBody = getRequestBody(exchange);
        JsonObject cuentaData = gson.fromJson(requestBody, JsonObject.class);
        
        // Crear DTO
        vCuenta_DTO nuevaCuenta = new vCuenta_DTO();
        nuevaCuenta.setIdSucursal(cuentaData.get("idSucursal").getAsString());
        nuevaCuenta.setCuentaGuid(UUID.randomUUID().toString());
        nuevaCuenta.setNumeroCuenta(cuentaData.get("numeroCuenta").getAsString());
        nuevaCuenta.setSaldoActual(new BigDecimal(cuentaData.get("saldoInicial").getAsString()));
        nuevaCuenta.setEstado(cuentaData.get("estado").getAsString());
        nuevaCuenta.setFechaApertura(LocalDate.now());
        nuevaCuenta.setIdCliente(cuentaData.get("idCliente").getAsInt());
        nuevaCuenta.setIdTipoCuenta(cuentaData.get("idTipoCuenta").getAsInt());
        
        // Guardar en BD
        boolean resultado = cuentaBL.add(nuevaCuenta);
        
        JsonObject response = new JsonObject();
        if (resultado) {
            response.addProperty("success", true);
            response.addProperty("message", "Cuenta creada exitosamente");
            response.add("cuenta", gson.toJsonTree(nuevaCuenta));
            sendJsonResponse(exchange, 201, response);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "No se pudo crear la cuenta");
            sendJsonResponse(exchange, 500, response);
        }
    }

    /**
     * PUT - Actualizar una cuenta existente
     */
    private void handlePut(HttpExchange exchange) throws Exception {
        String requestBody = getRequestBody(exchange);
        vCuenta_DTO cuenta = gson.fromJson(requestBody, vCuenta_DTO.class);
        
        boolean resultado = cuentaBL.update(cuenta);
        
        JsonObject response = new JsonObject();
        if (resultado) {
            response.addProperty("success", true);
            response.addProperty("message", "Cuenta actualizada exitosamente");
            sendJsonResponse(exchange, 200, response);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "No se pudo actualizar la cuenta");
            sendJsonResponse(exchange, 500, response);
        }
    }

    /**
     * DELETE - Eliminar una cuenta
     */
    private void handleDelete(HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.contains("id=")) {
            int id = Integer.parseInt(query.split("id=")[1]);
            boolean resultado = cuentaBL.delete(id);
            
            JsonObject response = new JsonObject();
            if (resultado) {
                response.addProperty("success", true);
                response.addProperty("message", "Cuenta eliminada exitosamente");
                sendJsonResponse(exchange, 200, response);
            } else {
                response.addProperty("success", false);
                response.addProperty("message", "No se pudo eliminar la cuenta");
                sendJsonResponse(exchange, 500, response);
            }
        } else {
            sendErrorResponse(exchange, "Falta el parámetro 'id'");
        }
    }

    // Métodos auxiliares
    private String getRequestBody(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private void sendJsonResponse(HttpExchange exchange, int statusCode, Object data) throws IOException {
        String jsonResponse = gson.toJson(data);
        exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, jsonResponse.getBytes(StandardCharsets.UTF_8).length);
        OutputStream os = exchange.getResponseBody();
        os.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
        os.close();
    }

    private void sendErrorResponse(HttpExchange exchange, String message) throws IOException {
        JsonObject errorResponse = new JsonObject();
        errorResponse.addProperty("success", false);
        errorResponse.addProperty("message", message);
        sendJsonResponse(exchange, 500, errorResponse);
    }
}
