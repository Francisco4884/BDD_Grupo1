package Server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.*;

/**
 * Servidor HTTP simple para servir archivos HTML y manejar API REST
 */
public class SimpleHTTPServer {
    private static final int PORT = 8080;
    private static HttpServer server;

    public static void main(String[] args) throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        
        // Manejador para archivos estáticos (HTML, CSS, JS)
        server.createContext("/", new StaticFileHandler());
        
        // API REST endpoints
        server.createContext("/api/login", new Server.Controllers.LoginController());
        server.createContext("/api/clientes", new Server.Controllers.ClienteController());
        server.createContext("/api/cuentas", new Server.Controllers.CuentaController());
        server.createContext("/api/transacciones", new Server.Controllers.TransaccionController());
        server.createContext("/api/usuarios", new Server.Controllers.UsuarioController());
        
        server.setExecutor(null);
        server.start();
        
        System.out.println("==============================================");
        System.out.println("Servidor iniciado en: http://localhost:" + PORT);
        System.out.println("==============================================");
        System.out.println("Endpoints disponibles:");
        System.out.println("  - http://localhost:" + PORT + "/login.html");
        System.out.println("  - http://localhost:" + PORT + "/dashboard.html");
        System.out.println("  - http://localhost:" + PORT + "/api/login");
        System.out.println("  - http://localhost:" + PORT + "/api/clientes");
        System.out.println("  - http://localhost:" + PORT + "/api/cuentas");
        System.out.println("==============================================");
    }
    
    /**
     * Manejador para servir archivos estáticos
     */
    static class StaticFileHandler implements HttpHandler {
        private static final String BASE_PATH = "src/UserInterface";
        
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            
            // Si es la raíz, redirigir a login
            if (path.equals("/")) {
                path = "/login.html";
            }
            
            // Construir ruta al archivo
            File file = new File(BASE_PATH + path);
            
            if (file.exists() && file.isFile()) {
                // Determinar tipo de contenido
                String contentType = getContentType(path);
                
                // Leer archivo
                byte[] bytes = Files.readAllBytes(file.toPath());
                
                // Enviar respuesta
                exchange.getResponseHeaders().set("Content-Type", contentType);
                exchange.sendResponseHeaders(200, bytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
            } else {
                // Archivo no encontrado
                String response = "404 - Archivo no encontrado: " + path;
                exchange.sendResponseHeaders(404, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
        
        private String getContentType(String path) {
            if (path.endsWith(".html")) return "text/html; charset=UTF-8";
            if (path.endsWith(".css")) return "text/css";
            if (path.endsWith(".js")) return "application/javascript";
            if (path.endsWith(".json")) return "application/json";
            if (path.endsWith(".png")) return "image/png";
            if (path.endsWith(".jpg") || path.endsWith(".jpeg")) return "image/jpeg";
            return "text/plain";
        }
    }
}
