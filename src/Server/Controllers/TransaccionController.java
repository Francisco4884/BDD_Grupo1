package Server.Controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import BusinessLogic.vTransaccion_BL;
import DataAccess.DTO.vTransaccion_DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Controlador para manejar operaciones de Transacciones
 */
public class TransaccionController implements HttpHandler {
    private vTransaccion_BL transaccionBL = new vTransaccion_BL();
    private Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
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
                default:
                    exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendErrorResponse(exchange, "Error en el servidor: " + e.getMessage());
        }
    }

    /**
     * GET - Obtener todas las transacciones o una específica
     */
    private void handleGet(HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.contains("id=")) {
            // Buscar por ID
            int id = Integer.parseInt(query.split("id=")[1]);
            vTransaccion_DTO transaccion = transaccionBL.getBy(id);
            
            if (transaccion != null) {
                sendJsonResponse(exchange, 200, transaccion);
            } else {
                JsonObject response = new JsonObject();
                response.addProperty("success", false);
                response.addProperty("message", "Transacción no encontrada");
                sendJsonResponse(exchange, 404, response);
            }
        } else {
            // Obtener todas las transacciones
            List<vTransaccion_DTO> transacciones = transaccionBL.getAll();
            sendJsonResponse(exchange, 200, transacciones);
        }
    }

    /**
     * POST - Crear una nueva transacción
     */
    private void handlePost(HttpExchange exchange) throws Exception {
        String requestBody = getRequestBody(exchange);
        JsonObject transaccionData = gson.fromJson(requestBody, JsonObject.class);
        
        // Crear DTO
        vTransaccion_DTO nuevaTransaccion = new vTransaccion_DTO();
        nuevaTransaccion.setIdSucursal(transaccionData.get("idSucursal").getAsString());
        nuevaTransaccion.setTransaccionGuid(UUID.randomUUID().toString());
        nuevaTransaccion.setCuentaGuid(transaccionData.get("cuentaGuid").getAsString());
        nuevaTransaccion.setIdTipoTransaccion(transaccionData.get("idTipoTransaccion").getAsInt());
        nuevaTransaccion.setFechaHora(LocalDateTime.now());
        nuevaTransaccion.setSaldoAnterior(new BigDecimal(transaccionData.get("saldoAnterior").getAsString()));
        nuevaTransaccion.setSaldoPosterior(new BigDecimal(transaccionData.get("saldoPosterior").getAsString()));
        nuevaTransaccion.setMonto(new BigDecimal(transaccionData.get("monto").getAsString()));
        nuevaTransaccion.setDescripcion(transaccionData.get("descripcion").getAsString());
        
        // Guardar en BD
        boolean resultado = transaccionBL.add(nuevaTransaccion);
        
        JsonObject response = new JsonObject();
        if (resultado) {
            response.addProperty("success", true);
            response.addProperty("message", "Transacción registrada exitosamente");
            response.add("transaccion", gson.toJsonTree(nuevaTransaccion));
            sendJsonResponse(exchange, 201, response);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "No se pudo registrar la transacción");
            sendJsonResponse(exchange, 500, response);
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
