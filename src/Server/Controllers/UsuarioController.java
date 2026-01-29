package Server.Controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import BusinessLogic.vUsuario_Completo_BL;
import DataAccess.DTO.vUsuario_Completo_DTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Controlador para manejar operaciones CRUD de Usuarios
 */
public class UsuarioController implements HttpHandler {
    private vUsuario_Completo_BL usuarioBL = new vUsuario_Completo_BL();
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
     * GET - Obtener todos los usuarios o uno específico
     */
    private void handleGet(HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.contains("username=")) {
            // Buscar por username
            String username = query.split("username=")[1];
            vUsuario_Completo_DTO usuario = usuarioBL.getByUsername(username);
            
            if (usuario != null) {
                sendJsonResponse(exchange, 200, usuario);
            } else {
                JsonObject response = new JsonObject();
                response.addProperty("success", false);
                response.addProperty("message", "Usuario no encontrado");
                sendJsonResponse(exchange, 404, response);
            }
        } else {
            // Obtener todos los usuarios
            List<vUsuario_Completo_DTO> usuarios = usuarioBL.getAll();
            sendJsonResponse(exchange, 200, usuarios);
        }
    }

    /**
     * POST - Crear un nuevo usuario
     */
    private void handlePost(HttpExchange exchange) throws Exception {
        String requestBody = getRequestBody(exchange);
        JsonObject usuarioData = gson.fromJson(requestBody, JsonObject.class);
        
        // Crear DTO
        vUsuario_Completo_DTO nuevoUsuario = new vUsuario_Completo_DTO();
        nuevoUsuario.setIdSucursal(usuarioData.get("idSucursal").getAsString());
        nuevoUsuario.setUsuarioGuid(UUID.randomUUID().toString());
        nuevoUsuario.setUsername(usuarioData.get("username").getAsString());
        nuevoUsuario.setPasswordHash(usuarioData.get("password").getAsString()); // En producción, hashear
        nuevoUsuario.setNombreCompleto(usuarioData.get("nombreCompleto").getAsString());
        nuevoUsuario.setCorreo(usuarioData.get("correo").getAsString());
        nuevoUsuario.setEstado(usuarioData.get("estado").getAsString());
        nuevoUsuario.setFechaCreacion(LocalDateTime.now());
        
        // Guardar en BD
        boolean resultado = usuarioBL.add(nuevoUsuario);
        
        JsonObject response = new JsonObject();
        if (resultado) {
            response.addProperty("success", true);
            response.addProperty("message", "Usuario creado exitosamente");
            response.add("usuario", gson.toJsonTree(nuevoUsuario));
            sendJsonResponse(exchange, 201, response);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "No se pudo crear el usuario");
            sendJsonResponse(exchange, 500, response);
        }
    }

    /**
     * PUT - Actualizar un usuario existente
     */
    private void handlePut(HttpExchange exchange) throws Exception {
        String requestBody = getRequestBody(exchange);
        vUsuario_Completo_DTO usuario = gson.fromJson(requestBody, vUsuario_Completo_DTO.class);
        
        boolean resultado = usuarioBL.update(usuario);
        
        JsonObject response = new JsonObject();
        if (resultado) {
            response.addProperty("success", true);
            response.addProperty("message", "Usuario actualizado exitosamente");
            sendJsonResponse(exchange, 200, response);
        } else {
            response.addProperty("success", false);
            response.addProperty("message", "No se pudo actualizar el usuario");
            sendJsonResponse(exchange, 500, response);
        }
    }

    /**
     * DELETE - Eliminar un usuario
     */
    private void handleDelete(HttpExchange exchange) throws Exception {
        String query = exchange.getRequestURI().getQuery();
        
        if (query != null && query.contains("id=")) {
            int id = Integer.parseInt(query.split("id=")[1]);
            boolean resultado = usuarioBL.delete(id);
            
            JsonObject response = new JsonObject();
            if (resultado) {
                response.addProperty("success", true);
                response.addProperty("message", "Usuario eliminado exitosamente");
                sendJsonResponse(exchange, 200, response);
            } else {
                response.addProperty("success", false);
                response.addProperty("message", "No se pudo eliminar el usuario");
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
