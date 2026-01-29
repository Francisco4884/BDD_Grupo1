package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import DataAccess.DTO.vUsuario_Completo_DTO;

public class vUsuario_Completo_DAO extends SQLDataHelper implements IDAO<vUsuario_Completo_DTO> {
    
    @Override
    public boolean create(vUsuario_Completo_DTO u) throws Exception {
        String sql = """
            INSERT INTO vUsuario_Completo
            (idSucursal, UsuarioGuid, username, passwordHash, nombreCompleto, correo, estado, fechaCreacion)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, u.getIdSucursal());
        ps.setString(2, u.getUsuarioGuid());
        ps.setString(3, u.getUsername());
        ps.setString(4, u.getPasswordHash());
        ps.setString(5, u.getNombreCompleto());
        ps.setString(6, u.getCorreo());
        ps.setString(7, u.getEstado());
        ps.setTimestamp(8, Timestamp.valueOf(u.getFechaCreacion()));
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public List<vUsuario_Completo_DTO> readAll() throws Exception {
        List<vUsuario_Completo_DTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vUsuario_Completo";
        Connection conn = openConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new vUsuario_Completo_DTO(
                rs.getString("idSucursal"),
                rs.getString("UsuarioGuid"),
                rs.getString("username"),
                rs.getString("passwordHash"),
                rs.getString("nombreCompleto"),
                rs.getString("correo"),
                rs.getString("estado"),
                rs.getTimestamp("fechaCreacion").toLocalDateTime()
            ));
        }
        return lista;
    }
    
    @Override
    public vUsuario_Completo_DTO read(Integer id) throws Exception {
        // Nota: Esta vista no tiene un idUsuario numérico, usaremos username como criterio
        String sql = "SELECT * FROM vUsuario_Completo WHERE username = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.valueOf(id)); // Adaptación temporal
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vUsuario_Completo_DTO(
            rs.getString("idSucursal"),
            rs.getString("UsuarioGuid"),
            rs.getString("username"),
            rs.getString("passwordHash"),
            rs.getString("nombreCompleto"),
            rs.getString("correo"),
            rs.getString("estado"),
            rs.getTimestamp("fechaCreacion").toLocalDateTime()
        ) : null;
    }
    
    // Método adicional para buscar por username (más apropiado)
    public vUsuario_Completo_DTO readByUsername(String username) throws Exception {
        String sql = "SELECT * FROM vUsuario_Completo WHERE username = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vUsuario_Completo_DTO(
            rs.getString("idSucursal"),
            rs.getString("UsuarioGuid"),
            rs.getString("username"),
            rs.getString("passwordHash"),
            rs.getString("nombreCompleto"),
            rs.getString("correo"),
            rs.getString("estado"),
            rs.getTimestamp("fechaCreacion").toLocalDateTime()
        ) : null;
    }
    
    @Override
    public boolean update(vUsuario_Completo_DTO u) throws Exception {
        String sql = """
            UPDATE vUsuario_Completo
            SET nombreCompleto = ?, correo = ?, estado = ?
            WHERE UsuarioGuid = ?
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, u.getNombreCompleto());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getEstado());
        ps.setString(4, u.getUsuarioGuid());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        // Eliminar por username
        String sql = "UPDATE vUsuario_Completo SET estado = 'X' WHERE username = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, String.valueOf(id));
        return ps.executeUpdate() > 0;
    }
    
    // Método adicional para eliminar por GUID (más apropiado)
    public boolean deleteByGuid(String usuarioGuid) throws Exception {
        String sql = "UPDATE vUsuario_Completo SET estado = 'X' WHERE UsuarioGuid = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuarioGuid);
        return ps.executeUpdate() > 0;
    }
}