
import BusinessLogic.vCliente_BL;
import DataAccess.DTO.vCliente_DTO;
import Server.SimpleHTTPServer;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class App {
    public static void main(String[] args) {
        if (args != null && args.length > 0 && "server".equalsIgnoreCase(args[0])) {
            try {
                SimpleHTTPServer.main(new String[0]);
            } catch (Exception e) {
                System.err.println("Error al iniciar el servidor: " + e.getMessage());
                e.printStackTrace();
            }
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("==============================================");
            System.out.println("Sistema Bancario BAD - Consola");
            System.out.println("==============================================");
            System.out.println("1) Registrar cliente en SQL Server");
            System.out.println("2) Iniciar servidor HTTP (igual que App.java server)");
            System.out.print("Seleccione una opción (1/2): ");

            String opcion = scanner.nextLine().trim();
            if ("2".equals(opcion)) {
                try {
                    SimpleHTTPServer.main(new String[0]);
                } catch (Exception e) {
                    System.err.println("Error al iniciar el servidor: " + e.getMessage());
                    e.printStackTrace();
                }
                return;
            }

            vCliente_BL clienteBL = new vCliente_BL();
            vCliente_DTO dto = new vCliente_DTO();

            System.out.print("Id Sucursal (ej: 1): ");
            dto.setIdSucursalOrigen(scanner.nextLine().trim());

            System.out.print("Cédula: ");
            dto.setCedula(scanner.nextLine().trim());

            System.out.print("Nombre completo: ");
            dto.setNombreCompleto(scanner.nextLine().trim());

            System.out.print("Dirección: ");
            dto.setDireccion(scanner.nextLine().trim());

            System.out.print("Teléfono: ");
            dto.setTelefono(scanner.nextLine().trim());

            System.out.print("Email: ");
            dto.setEmail(scanner.nextLine().trim());

            System.out.print("Estado (A/I): ");
            String estado = scanner.nextLine().trim();
            dto.setEstado(estado.isEmpty() ? "A" : estado);

            dto.setClienteGuid(UUID.randomUUID().toString());
            dto.setFechaRegistro(LocalDate.now());

            boolean resultado = clienteBL.add(dto);
            if (resultado) {
                System.out.println("Cliente agregado correctamente en SQL Server.");
            } else {
                System.out.println("No se pudo agregar el cliente.");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
