package DataAccess.DTO;

public class vSucursal_DTO {
    private String idSucursal;
    private String nombre;
    private String ciudad;
    private String direccion;
    private String telefono;

    // Constructor vacío
    public vSucursal_DTO() {
    }

    // Constructor completo
    public vSucursal_DTO(String idSucursal, String nombre, String ciudad, 
                         String direccion, String telefono) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "vSucursal_DTO{" +
                "idSucursal='" + idSucursal + '\'' +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}