package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.vSucursal_DTO;

public class vSucursal_DAO extends SQLDataHelper implements IDAO<vSucursal_DTO> {
    
    @Override
    public boolean create(vSucursal_DTO s) throws Exception {
        String sql = """
            INSERT INTO vSucursal
            (idSucursal, nombre, ciudad, direccion, telefono)
            VALUES (?, ?, ?, ?, ?)
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s.getIdSucursal());
        ps.setString(2, s.getNombre());
        ps.setString(3, s.getCiudad());
        ps.setString(4, s.getDireccion());
        ps.setString(5, s.getTelefono());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public List<vSucursal_DTO> readAll() throws Exception {
        List<vSucursal_DTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vSucursal";
        Connection conn = openConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new vSucursal_DTO(
                rs.getString("idSucursal"),
                rs.getString("nombre"),
                rs.getString("ciudad"),
                rs.getString("direccion"),
                rs.getString("telefono")
            ));
        }
        return lista;
    }
    
    @Override
    public vSucursal_DTO read(Integer id) throws Exception {
        // Para sucursal, usamos el código de sucursal (String) en lugar de Integer
        throw new UnsupportedOperationException("Use readByCode(String) instead");
    }
    
    // Método apropiado para leer por código de sucursal
    public vSucursal_DTO readByCode(String idSucursal) throws Exception {
        String sql = "SELECT * FROM vSucursal WHERE idSucursal = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idSucursal);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vSucursal_DTO(
            rs.getString("idSucursal"),
            rs.getString("nombre"),
            rs.getString("ciudad"),
            rs.getString("direccion"),
            rs.getString("telefono")
        ) : null;
    }
    
    @Override
    public boolean update(vSucursal_DTO s) throws Exception {
        String sql = """
            UPDATE vSucursal
            SET nombre = ?, ciudad = ?, direccion = ?, telefono = ?
            WHERE idSucursal = ?
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, s.getNombre());
        ps.setString(2, s.getCiudad());
        ps.setString(3, s.getDireccion());
        ps.setString(4, s.getTelefono());
        ps.setString(5, s.getIdSucursal());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Use deleteByCode(String) instead");
    }
    
    // Método apropiado para eliminar por código de sucursal
    public boolean deleteByCode(String idSucursal) throws Exception {
        String sql = "DELETE FROM vSucursal WHERE idSucursal = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idSucursal);
        return ps.executeUpdate() > 0;
    }
}