package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.vCliente_DTO;

public class vCliente_DAO extends SQLDataHelper implements IDAO<vCliente_DTO> {
    
    @Override
    public boolean create(vCliente_DTO c) throws Exception {
        String sql = """
            INSERT INTO vCliente
            (idSucursalOrigen, ClienteGuid, cedula, nombreCompleto, direccion, telefono, email, fechaRegistro, estado)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getIdSucursalOrigen());
        ps.setString(2, c.getClienteGuid());
        ps.setString(3, c.getCedula());
        ps.setString(4, c.getNombreCompleto());
        ps.setString(5, c.getDireccion());
        ps.setString(6, c.getTelefono());
        ps.setString(7, c.getEmail());
        ps.setDate(8, Date.valueOf(c.getFechaRegistro()));
        ps.setString(9, c.getEstado());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public List<vCliente_DTO> readAll() throws Exception {
        List<vCliente_DTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vCliente";
        Connection conn = openConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new vCliente_DTO(
                rs.getString("idSucursalOrigen"),
                rs.getString("ClienteGuid"),
                rs.getInt("idCliente"),
                rs.getString("cedula"),
                rs.getString("nombreCompleto"),
                rs.getString("direccion"),
                rs.getString("telefono"),
                rs.getString("email"),
                rs.getDate("fechaRegistro").toLocalDate(),
                rs.getString("estado")
            ));
        }
        return lista;
    }
    
    @Override
    public vCliente_DTO read(Integer id) throws Exception {
        String sql = "SELECT * FROM vCliente WHERE idCliente = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vCliente_DTO(
            rs.getString("idSucursalOrigen"),
            rs.getString("ClienteGuid"),
            rs.getInt("idCliente"),
            rs.getString("cedula"),
            rs.getString("nombreCompleto"),
            rs.getString("direccion"),
            rs.getString("telefono"),
            rs.getString("email"),
            rs.getDate("fechaRegistro").toLocalDate(),
            rs.getString("estado")
        ) : null;
    }
    
    // Método adicional para buscar por GUID
    public vCliente_DTO readByGuid(String clienteGuid) throws Exception {
        String sql = "SELECT * FROM vCliente WHERE ClienteGuid = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, clienteGuid);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vCliente_DTO(
            rs.getString("idSucursalOrigen"),
            rs.getString("ClienteGuid"),
            rs.getInt("idCliente"),
            rs.getString("cedula"),
            rs.getString("nombreCompleto"),
            rs.getString("direccion"),
            rs.getString("telefono"),
            rs.getString("email"),
            rs.getDate("fechaRegistro").toLocalDate(),
            rs.getString("estado")
        ) : null;
    }
    
    // Método adicional para buscar por cédula
    public vCliente_DTO readByCedula(String cedula) throws Exception {
        String sql = "SELECT * FROM vCliente WHERE cedula = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, cedula);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vCliente_DTO(
            rs.getString("idSucursalOrigen"),
            rs.getString("ClienteGuid"),
            rs.getInt("idCliente"),
            rs.getString("cedula"),
            rs.getString("nombreCompleto"),
            rs.getString("direccion"),
            rs.getString("telefono"),
            rs.getString("email"),
            rs.getDate("fechaRegistro").toLocalDate(),
            rs.getString("estado")
        ) : null;
    }
    
    @Override
    public boolean update(vCliente_DTO c) throws Exception {
        String sql = """
            UPDATE vCliente
            SET direccion = ?, telefono = ?, email = ?
            WHERE idCliente = ?
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, c.getDireccion());
        ps.setString(2, c.getTelefono());
        ps.setString(3, c.getEmail());
        ps.setInt(4, c.getIdCliente());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        String sql = "UPDATE vCliente SET estado = 'X' WHERE idCliente = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }
}