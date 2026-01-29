package DataAccess.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class vTransaccion_DTO {
    private String idSucursal;
    private String transaccionGuid;
    private int idTransaccion;
    private String cuentaGuid;
    private int idTipoTransaccion;
    private LocalDateTime fechaHora;
    private BigDecimal saldoAnterior;
    private BigDecimal saldoPosterior;
    private BigDecimal monto;
    private String descripcion;

    // Constructor vacío
    public vTransaccion_DTO() {
    }

    // Constructor completo
    public vTransaccion_DTO(String idSucursal, String transaccionGuid, int idTransaccion, 
                            String cuentaGuid, int idTipoTransaccion, LocalDateTime fechaHora, 
                            BigDecimal saldoAnterior, BigDecimal saldoPosterior, 
                            BigDecimal monto, String descripcion) {
        this.idSucursal = idSucursal;
        this.transaccionGuid = transaccionGuid;
        this.idTransaccion = idTransaccion;
        this.cuentaGuid = cuentaGuid;
        this.idTipoTransaccion = idTipoTransaccion;
        this.fechaHora = fechaHora;
        this.saldoAnterior = saldoAnterior;
        this.saldoPosterior = saldoPosterior;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    // Constructor para creación (sin idTransaccion autogenerado)
    public vTransaccion_DTO(String idSucursal, String transaccionGuid, String cuentaGuid, 
                            int idTipoTransaccion, LocalDateTime fechaHora, 
                            BigDecimal saldoAnterior, BigDecimal saldoPosterior, 
                            BigDecimal monto, String descripcion) {
        this.idSucursal = idSucursal;
        this.transaccionGuid = transaccionGuid;
        this.cuentaGuid = cuentaGuid;
        this.idTipoTransaccion = idTipoTransaccion;
        this.fechaHora = fechaHora;
        this.saldoAnterior = saldoAnterior;
        this.saldoPosterior = saldoPosterior;
        this.monto = monto;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getTransaccionGuid() {
        return transaccionGuid;
    }

    public void setTransaccionGuid(String transaccionGuid) {
        this.transaccionGuid = transaccionGuid;
    }

    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCuentaGuid() {
        return cuentaGuid;
    }

    public void setCuentaGuid(String cuentaGuid) {
        this.cuentaGuid = cuentaGuid;
    }

    public int getIdTipoTransaccion() {
        return idTipoTransaccion;
    }

    public void setIdTipoTransaccion(int idTipoTransaccion) {
        this.idTipoTransaccion = idTipoTransaccion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public BigDecimal getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(BigDecimal saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public BigDecimal getSaldoPosterior() {
        return saldoPosterior;
    }

    public void setSaldoPosterior(BigDecimal saldoPosterior) {
        this.saldoPosterior = saldoPosterior;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "vTransaccion_DTO{" +
                "idSucursal='" + idSucursal + '\'' +
                ", transaccionGuid='" + transaccionGuid + '\'' +
                ", idTransaccion=" + idTransaccion +
                ", cuentaGuid='" + cuentaGuid + '\'' +
                ", idTipoTransaccion=" + idTipoTransaccion +
                ", fechaHora=" + fechaHora +
                ", saldoAnterior=" + saldoAnterior +
                ", saldoPosterior=" + saldoPosterior +
                ", monto=" + monto +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}