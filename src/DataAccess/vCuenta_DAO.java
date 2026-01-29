package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.vCuenta_DTO;

public class vCuenta_DAO extends SQLDataHelper implements IDAO<vCuenta_DTO> {
    
    @Override
    public boolean create(vCuenta_DTO c) throws Exception {
        String sql = """
            INSERT INTO vCuenta
            (idSucursal, CuentaGuid, numeroCuenta, saldoActual, estado, fechaApertura, idCliente, idTipoCuenta)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getIdSucursal());
        ps.setString(2, c.getCuentaGuid());
        ps.setString(3, c.getNumeroCuenta());
        ps.setBigDecimal(4, c.getSaldoActual());
        ps.setString(5, c.getEstado());
        ps.setDate(6, java.sql.Date.valueOf(c.getFechaApertura()));
        ps.setInt(7, c.getIdCliente());
        ps.setInt(8, c.getIdTipoCuenta());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public List<vCuenta_DTO> readAll() throws Exception {
        List<vCuenta_DTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vCuenta";
        Connection conn = openConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new vCuenta_DTO(
                rs.getString("idSucursal"),
                rs.getString("CuentaGuid"),
                rs.getInt("idCuenta"),
                rs.getString("numeroCuenta"),
                rs.getBigDecimal("saldoActual"),
                rs.getString("estado"),
                rs.getDate("fechaApertura").toLocalDate(),
                rs.getInt("idCliente"),
                rs.getInt("idTipoCuenta")
            ));
        }
        return lista;
    }
    
    @Override
    public vCuenta_DTO read(Integer id) throws Exception {
        String sql = "SELECT * FROM vCuenta WHERE idCuenta = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vCuenta_DTO(
            rs.getString("idSucursal"),
            rs.getString("CuentaGuid"),
            rs.getInt("idCuenta"),
            rs.getString("numeroCuenta"),
            rs.getBigDecimal("saldoActual"),
            rs.getString("estado"),
            rs.getDate("fechaApertura").toLocalDate(),
            rs.getInt("idCliente"),
            rs.getInt("idTipoCuenta")
        ) : null;
    }
    
    @Override
    public boolean update(vCuenta_DTO c) throws Exception {
        String sql = """
            UPDATE vCuenta
            SET saldoActual = ?, estado = ?
            WHERE idCuenta = ?
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setBigDecimal(1, c.getSaldoActual());
        ps.setString(2, c.getEstado());
        ps.setInt(3, c.getIdCuenta());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE vCuenta SET estado = 'X' WHERE idCuenta = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}