package DataAccess.DTO;

import java.time.LocalDateTime;

public class vUsuario_Completo_DTO {
    private String idSucursal;
    private String usuarioGuid;
    private String username;
    private String passwordHash;
    private String nombreCompleto;
    private String correo;
    private String estado;
    private LocalDateTime fechaCreacion;

    // Constructor vacío
    public vUsuario_Completo_DTO() {
    }

    // Constructor completo
    public vUsuario_Completo_DTO(String idSucursal, String usuarioGuid, String username, 
                                 String passwordHash, String nombreCompleto, String correo, 
                                 String estado, LocalDateTime fechaCreacion) {
        this.idSucursal = idSucursal;
        this.usuarioGuid = usuarioGuid;
        this.username = username;
        this.passwordHash = passwordHash;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // Constructor para creación
    public vUsuario_Completo_DTO(String idSucursal, String usuarioGuid, String username, 
                                 String passwordHash, String nombreCompleto, String correo, 
                                 String estado) {
        this.idSucursal = idSucursal;
        this.usuarioGuid = usuarioGuid;
        this.username = username;
        this.passwordHash = passwordHash;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.estado = estado;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y Setters
    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getUsuarioGuid() {
        return usuarioGuid;
    }

    public void setUsuarioGuid(String usuarioGuid) {
        this.usuarioGuid = usuarioGuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "vUsuario_Completo_DTO{" +
                "idSucursal='" + idSucursal + '\'' +
                ", usuarioGuid='" + usuarioGuid + '\'' +
                ", username='" + username + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", estado='" + estado + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}