package DataAccess.DTO;

import java.time.LocalDateTime;

public class vUsuario_Perfil_Global_DTO {
    private String idSucursal;
    private String usuarioGuid;
    private String nombreCompleto;
    private String correo;
    private LocalDateTime fechaCreacion;

    // Constructor vacío
    public vUsuario_Perfil_Global_DTO() {
    }

    // Constructor completo
    public vUsuario_Perfil_Global_DTO(String idSucursal, String usuarioGuid, 
                                      String nombreCompleto, String correo, 
                                      LocalDateTime fechaCreacion) {
        this.idSucursal = idSucursal;
        this.usuarioGuid = usuarioGuid;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.fechaCreacion = fechaCreacion;
    }

    // Constructor para creación
    public vUsuario_Perfil_Global_DTO(String idSucursal, String usuarioGuid, 
                                      String nombreCompleto, String correo) {
        this.idSucursal = idSucursal;
        this.usuarioGuid = usuarioGuid;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "vUsuario_Perfil_Global_DTO{" +
                "idSucursal='" + idSucursal + '\'' +
                ", usuarioGuid='" + usuarioGuid + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}