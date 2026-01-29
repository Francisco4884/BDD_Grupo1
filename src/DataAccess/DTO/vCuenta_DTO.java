package DataAccess.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public class vCuenta_DTO {
    private String idSucursal;
    private String cuentaGuid;
    private int idCuenta;
    private String numeroCuenta;
    private BigDecimal saldoActual;
    private String estado;
    private LocalDate fechaApertura;
    private int idCliente;
    private int idTipoCuenta;

    // Constructor vacío
    public vCuenta_DTO() {
    }

    // Constructor completo
    public vCuenta_DTO(String idSucursal, String cuentaGuid, int idCuenta, 
                       String numeroCuenta, BigDecimal saldoActual, String estado, 
                       LocalDate fechaApertura, int idCliente, int idTipoCuenta) {
        this.idSucursal = idSucursal;
        this.cuentaGuid = cuentaGuid;
        this.idCuenta = idCuenta;
        this.numeroCuenta = numeroCuenta;
        this.saldoActual = saldoActual;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
        this.idCliente = idCliente;
        this.idTipoCuenta = idTipoCuenta;
    }

    // Constructor para creación (sin idCuenta autogenerado)
    public vCuenta_DTO(String idSucursal, String cuentaGuid, String numeroCuenta, 
                       BigDecimal saldoActual, String estado, LocalDate fechaApertura, 
                       int idCliente, int idTipoCuenta) {
        this.idSucursal = idSucursal;
        this.cuentaGuid = cuentaGuid;
        this.numeroCuenta = numeroCuenta;
        this.saldoActual = saldoActual;
        this.estado = estado;
        this.fechaApertura = fechaApertura;
        this.idCliente = idCliente;
        this.idTipoCuenta = idTipoCuenta;
    }

    // Getters y Setters
    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getCuentaGuid() {
        return cuentaGuid;
    }

    public void setCuentaGuid(String cuentaGuid) {
        this.cuentaGuid = cuentaGuid;
    }

    public int getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(LocalDate fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdTipoCuenta() {
        return idTipoCuenta;
    }

    public void setIdTipoCuenta(int idTipoCuenta) {
        this.idTipoCuenta = idTipoCuenta;
    }

    @Override
    public String toString() {
        return "vCuenta_DTO{" +
                "idSucursal='" + idSucursal + '\'' +
                ", cuentaGuid='" + cuentaGuid + '\'' +
                ", idCuenta=" + idCuenta +
                ", numeroCuenta='" + numeroCuenta + '\'' +
                ", saldoActual=" + saldoActual +
                ", estado='" + estado + '\'' +
                ", fechaApertura=" + fechaApertura +
                ", idCliente=" + idCliente +
                ", idTipoCuenta=" + idTipoCuenta +
                '}';
    }
}