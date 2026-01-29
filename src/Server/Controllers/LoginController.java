package Server.Controllers;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.nio.charset.StandardCharsets;
import BusinessLogic.vUsuario_Completo_BL;
import DataAccess.DTO.vUsuario_Completo_DTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Controlador para manejar autenticación de usuarios
 */
public class LoginController implements HttpHandler {
    private vUsuario_Completo_BL usuarioBL = new vUsuario_Completo_BL();
    private Gson gson = new Gson();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Habilitar CORS
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
        
        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
            try {
                // Leer el cuerpo de la petición
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                
                // Parse JSON
                JsonObject loginData = gson.fromJson(sb.toString(), JsonObject.class);
                String username = loginData.get("username").getAsString();
                String password = loginData.get("password").getAsString();
                String sucursal = loginData.get("sucursal").getAsString();
                
                // Buscar usuario
                vUsuario_Completo_DTO usuario = usuarioBL.getByUsername(username);
                
                JsonObject response = new JsonObject();
                
                if (usuario != null) {
                    // Verificar contraseña (en producción debes usar hash)
                    if (usuario.getPasswordHash().equals(password)) {
                        // Login exitoso
                        response.addProperty("success", true);
                        response.addProperty("message", "Login exitoso");
                        
                        JsonObject userData = new JsonObject();
                        userData.addProperty("username", usuario.getUsername());
                        userData.addProperty("nombreCompleto", usuario.getNombreCompleto());
                        userData.addProperty("correo", usuario.getCorreo());
                        userData.addProperty("idSucursal", usuario.getIdSucursal());
                        userData.addProperty("estado", usuario.getEstado());
                        
                        response.add("usuario", userData);
                    } else {
                        // Contraseña incorrecta
                        response.addProperty("success", false);
                        response.addProperty("message", "Contraseña incorrecta");
                    }
                } else {
                    // Usuario no encontrado
                    response.addProperty("success", false);
                    response.addProperty("message", "Usuario no encontrado");
                }
                
                // Enviar respuesta
                String jsonResponse = gson.toJson(response);
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(200, jsonResponse.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = exchange.getResponseBody();
                os.write(jsonResponse.getBytes(StandardCharsets.UTF_8));
                os.close();
                
            } catch (Exception e) {
                e.printStackTrace();
                JsonObject errorResponse = new JsonObject();
                errorResponse.addProperty("success", false);
                errorResponse.addProperty("message", "Error en el servidor: " + e.getMessage());
                
                String jsonError = gson.toJson(errorResponse);
                exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
                exchange.sendResponseHeaders(500, jsonError.getBytes(StandardCharsets.UTF_8).length);
                OutputStream os = exchange.getResponseBody();
                os.write(jsonError.getBytes(StandardCharsets.UTF_8));
                os.close();
            }
        } else {
            // Método no permitido
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
