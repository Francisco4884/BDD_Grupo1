package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.vTransaccion_DTO;

public class vTransaccion_DAO extends SQLDataHelper implements IDAO<vTransaccion_DTO> {
    
    @Override
    public boolean create(vTransaccion_DTO t) throws Exception {
        String sql = """
            INSERT INTO vTransaccion
            (idSucursal, TransaccionGuid, CuentaGuid, idTipoTransaccion, fechaHora, 
             saldoAnterior, saldoPosterior, monto, descripcion)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, t.getIdSucursal());
        ps.setString(2, t.getTransaccionGuid());
        ps.setString(3, t.getCuentaGuid());
        ps.setInt(4, t.getIdTipoTransaccion());
        ps.setTimestamp(5, Timestamp.valueOf(t.getFechaHora()));
        ps.setBigDecimal(6, t.getSaldoAnterior());
        ps.setBigDecimal(7, t.getSaldoPosterior());
        ps.setBigDecimal(8, t.getMonto());
        ps.setString(9, t.getDescripcion());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public List<vTransaccion_DTO> readAll() throws Exception {
        List<vTransaccion_DTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vTransaccion";
        Connection conn = openConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new vTransaccion_DTO(
                rs.getString("idSucursal"),
                rs.getString("TransaccionGuid"),
                rs.getInt("idTransaccion"),
                rs.getString("CuentaGuid"),
                rs.getInt("idTipoTransaccion"),
                rs.getTimestamp("fechaHora").toLocalDateTime(),
                rs.getBigDecimal("saldoAnterior"),
                rs.getBigDecimal("saldoPosterior"),
                rs.getBigDecimal("monto"),
                rs.getString("descripcion")
            ));
        }
        return lista;
    }
    
    @Override
    public vTransaccion_DTO read(Integer id) throws Exception {
        String sql = "SELECT * FROM vTransaccion WHERE idTransaccion = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vTransaccion_DTO(
            rs.getString("idSucursal"),
            rs.getString("TransaccionGuid"),
            rs.getInt("idTransaccion"),
            rs.getString("CuentaGuid"),
            rs.getInt("idTipoTransaccion"),
            rs.getTimestamp("fechaHora").toLocalDateTime(),
            rs.getBigDecimal("saldoAnterior"),
            rs.getBigDecimal("saldoPosterior"),
            rs.getBigDecimal("monto"),
            rs.getString("descripcion")
        ) : null;
    }
    
    @Override
    public boolean update(vTransaccion_DTO t) throws Exception {
        String sql = """
            UPDATE vTransaccion
            SET descripcion = ?
            WHERE idTransaccion = ?
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, t.getDescripcion());
        ps.setInt(2, t.getIdTransaccion());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        // Las transacciones normalmente no se eliminan, pero incluyo la implementación
        String sql = "DELETE FROM vTransaccion WHERE idTransaccion = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}