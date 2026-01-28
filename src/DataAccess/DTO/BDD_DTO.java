package DataAccess.DTO;

public class BDD_DTO {
    private Integer idPersonaRol; 
    private String Nombre;       
    private String Observacion;  
    private String Estado;       
    private String FechaCrea;    
    private String FechaModifica;

    public BDD_DTO(){}
    public BDD_DTO(String Nombre){}
    public BDD_DTO( int    idPersonaRol,
                        String Nombre,       
                        String Observacion,  
                        String Estado,       
                        String FechaCrea,    
                        String FechaModifica){
        this.idPersonaRol= idPersonaRol;
        this.Nombre= Nombre;       
        this.Observacion= Observacion;  
        this.Estado= Estado;       
        this.FechaCrea= FechaCrea;    
        this.FechaModifica= FechaModifica;                        
    }

    public Integer getIdPersonaRol() {
        return idPersonaRol;
    }
    public void setIdPersonaRol(Integer idPersonaRol) {
        this.idPersonaRol = idPersonaRol;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }
    public String getObservacion() {
        return Observacion;
    }
    public void setObservacion(String observacion) {
        Observacion = observacion;
    }
    public String getEstado() {
        return Estado;
    }
    public void setEstado(String estado) {
        Estado = estado;
    }
    public String getFechaCrea() {
        return FechaCrea;
    }
    public void setFechaCrea(String fechaCrea) {
        FechaCrea = fechaCrea;
    }
    public String getFechaModifica() {
        return FechaModifica;
    }
    public void setFechaModifica(String fechaModifica) {
        FechaModifica = fechaModifica;
    }

    @Override
    public String toString() {
        return getClass().getName()
        + "\n idPersonaRol:  " + getIdPersonaRol() 
        + "\n Nombre:        " + getNombre()       
        + "\n Observacion:   " + getObservacion()  
        + "\n Estado:        " + getEstado()       
        + "\n FechaCrea:     " + getFechaCrea()    
        + "\n FechaModifica: " + getFechaModifica()  ;
    }
}
