
import BusinessLogic.BDD_BL;
import DataAccess.DTO.BDD_DTO;

public class App {
    public static void main(String[] args) {
        BDD_BL BD = new BDD_BL();
        try {
            // Crear un objeto BDD_DTO con los datos
            BDD_DTO dto = new BDD_DTO();
            dto.setNombre("Usuario1");
            dto.setObservacion("Observacion1");
            dto.setEstado("Activo");
            
            // Agregar datos a la base de datos
            boolean resultado = BD.add(dto);
            
            if (resultado) {
                System.out.println("Datos añadidos correctamente.");
            } else {
                System.out.println("No se pudo agregar los datos.");
            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
