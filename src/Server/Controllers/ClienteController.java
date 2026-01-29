package Server.Controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import BusinessLogic.vCliente_BL;
import DataAccess.DTO.vCliente_DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Controlador para manejar operaciones CRUD de Clientes
 */
public class ClienteController implements HttpHandler {
    private vCliente_BL clienteBL = new vCliente_BL();
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
     * GET - Obtener todos los clientes o uno específico
     */
    private void handleGet(HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.contains("cedula=")) {
            // Buscar por cédula
            String cedula = query.split("cedula=")[1];
            vCliente_DTO cliente = clienteBL.getByCedula(cedula);
            
            if (cliente != null) {
                sendJsonResponse(exchange, 200, cliente);
            } else {
                JsonObject response = new JsonObject();
                response.addProperty("success", false);
                response.addProperty("message", "Cliente no encontrado");
                sendJsonResponse(exchange, 404, response);
            }
        } else if (query != null && query.contains("id=")) {
            // Buscar por ID
            int id = Integer.parseInt(query.split("id=")[1]);
            vCliente_DTO cliente = clienteBL.getBy(id);
            
            if (cliente != null) {
                sendJsonResponse(exchange, 200, cliente);
            } else {
                JsonObject response = new JsonObject();
                response.addProperty("success", false);
                response.addProperty("message", "Cliente no encontrado");
                sendJsonResponse(exchange, 404, response);
            }
        } else {
            // Obtener todos los clientes
            List<vCliente_DTO> clientes = clienteBL.getAll();
            sendJsonResponse(exchange, 200, clientes);
        }
    }

    /**
     * POST - Crear un nuevo cliente
     */
    private void handlePost(HttpExchange exchange) throws Exception {
        String requestBody = getRequestBody(exchange);
        JsonObject clienteData = gson.fromJson(requestBody, JsonObject.class);
        
        // Crear DTO
        vCliente_DTO nuevoCliente = new vCliente_DTO();
        nuevoCliente.setIdSucursalOrigen(clienteData.get("idSucursalOrigen").getAsString());
        nuevoCliente.setClienteGuid(UUID.randomUUID().toString());
        nuevoCliente.setCedula(clienteData.get("cedula").getAsString());
        nuevoCliente.setNombreCompleto(clienteData.get("nombreCompleto").getAsString());
        nuevoCliente.setDireccion(clienteData.get("direccion").getAsString());
        nuevoCliente.setTelefono(clienteData.get("telefono").getAsString());
        nuevoCliente.setEmail(clienteData.get("email").getAsString());
        nuevoCliente.setFechaRegistro(LocalDate.now());
        nuevoCliente.setEstado(clienteData.get("estado").getAsString());
        
        // Guardar en BD
        boolean resultado = clienteBL.add(nuevoCliente);
        
        JsonObject response = new JsonObject();
        if (resultado) {
            response.addProperty("success", true);
            response.addProperty("message", "Cliente registrado exitosamente");
            response.add("cliente", gson.toJsonTree(nuevoCliente));
            sendJsonResponse(exchange, 201, response);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "No se pudo registrar el cliente");
            sendJsonResponse(exchange, 500, response);
        }
    }

    /**
     * PUT - Actualizar un cliente existente
     */
    private void handlePut(HttpExchange exchange) throws Exception {
        String requestBody = getRequestBody(exchange);
        vCliente_DTO cliente = gson.fromJson(requestBody, vCliente_DTO.class);
        
        boolean resultado = clienteBL.update(cliente);
        
        JsonObject response = new JsonObject();
        if (resultado) {
            response.addProperty("success", true);
            response.addProperty("message", "Cliente actualizado exitosamente");
            sendJsonResponse(exchange, 200, response);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "No se pudo actualizar el cliente");
            sendJsonResponse(exchange, 500, response);
        }
    }

    /**
     * DELETE - Eliminar un cliente
     */
    private void handleDelete(HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.contains("id=")) {
            int id = Integer.parseInt(query.split("id=")[1]);
            boolean resultado = clienteBL.delete(id);
            
            JsonObject response = new JsonObject();
            if (resultado) {
                response.addProperty("success", true);
                response.addProperty("message", "Cliente eliminado exitosamente");
                sendJsonResponse(exchange, 200, response);
            } else {
                response.addProperty("success", false);
                response.addProperty("message", "No se pudo eliminar el cliente");
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
