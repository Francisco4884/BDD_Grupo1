package DataAccess.DTO;

import java.time.LocalDate;

public class vCliente_DTO {
    private String idSucursalOrigen;
    private String clienteGuid;
    private int idCliente;
    private String cedula;
    private String nombreCompleto;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDate fechaRegistro;
    private String estado;

    // Constructor vacío
    public vCliente_DTO() {
    }

    // Constructor completo
    public vCliente_DTO(String idSucursalOrigen, String clienteGuid, int idCliente, 
                        String cedula, String nombreCompleto, String direccion, 
                        String telefono, String email, LocalDate fechaRegistro, String estado) {
        this.idSucursalOrigen = idSucursalOrigen;
        this.clienteGuid = clienteGuid;
        this.idCliente = idCliente;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Constructor para creación (sin idCliente autogenerado)
    public vCliente_DTO(String idSucursalOrigen, String clienteGuid, String cedula, 
                        String nombreCompleto, String direccion, String telefono, 
                        String email, LocalDate fechaRegistro, String estado) {
        this.idSucursalOrigen = idSucursalOrigen;
        this.clienteGuid = clienteGuid;
        this.cedula = cedula;
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Getters y Setters
    public String getIdSucursalOrigen() {
        return idSucursalOrigen;
    }

    public void setIdSucursalOrigen(String idSucursalOrigen) {
        this.idSucursalOrigen = idSucursalOrigen;
    }

    public String getClienteGuid() {
        return clienteGuid;
    }

    public void setClienteGuid(String clienteGuid) {
        this.clienteGuid = clienteGuid;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "vCliente_DTO{" +
                "idSucursalOrigen='" + idSucursalOrigen + '\'' +
                ", clienteGuid='" + clienteGuid + '\'' +
                ", idCliente=" + idCliente +
                ", cedula='" + cedula + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", estado='" + estado + '\'' +
                '}';
    }
}