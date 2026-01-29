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
import DataAccess.DTO.vUsuario_Perfil_Global_DTO;

public class vUsuario_Perfil_Global_DAO extends SQLDataHelper implements IDAO<vUsuario_Perfil_Global_DTO> {
    
    @Override
    public boolean create(vUsuario_Perfil_Global_DTO u) throws Exception {
        String sql = """
            INSERT INTO vUsuario_Perfil_Global
            (idSucursal, UsuarioGuid, nombreCompleto, correo, fechaCreacion)
            VALUES (?, ?, ?, ?, ?)
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, u.getIdSucursal());
        ps.setString(2, u.getUsuarioGuid());
        ps.setString(3, u.getNombreCompleto());
        ps.setString(4, u.getCorreo());
        ps.setTimestamp(5, Timestamp.valueOf(u.getFechaCreacion()));
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public List<vUsuario_Perfil_Global_DTO> readAll() throws Exception {
        List<vUsuario_Perfil_Global_DTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vUsuario_Perfil_Global";
        Connection conn = openConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            lista.add(new vUsuario_Perfil_Global_DTO(
                rs.getString("idSucursal"),
                rs.getString("UsuarioGuid"),
                rs.getString("nombreCompleto"),
                rs.getString("correo"),
                rs.getTimestamp("fechaCreacion").toLocalDateTime()
            ));
        }
        return lista;
    }
    
    @Override
    public vUsuario_Perfil_Global_DTO read(Integer id) throws Exception {
        throw new UnsupportedOperationException("Use readByGuid(String) instead");
    }
    
    // Método apropiado para leer por UsuarioGuid
    public vUsuario_Perfil_Global_DTO readByGuid(String usuarioGuid) throws Exception {
        String sql = "SELECT * FROM vUsuario_Perfil_Global WHERE UsuarioGuid = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuarioGuid);
        ResultSet rs = ps.executeQuery();
        return rs.next() ? new vUsuario_Perfil_Global_DTO(
            rs.getString("idSucursal"),
            rs.getString("UsuarioGuid"),
            rs.getString("nombreCompleto"),
            rs.getString("correo"),
            rs.getTimestamp("fechaCreacion").toLocalDateTime()
        ) : null;
    }
    
    // Método para leer por sucursal
    public List<vUsuario_Perfil_Global_DTO> readBySucursal(String idSucursal) throws Exception {
        List<vUsuario_Perfil_Global_DTO> lista = new ArrayList<>();
        String sql = "SELECT * FROM vUsuario_Perfil_Global WHERE idSucursal = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, idSucursal);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            lista.add(new vUsuario_Perfil_Global_DTO(
                rs.getString("idSucursal"),
                rs.getString("UsuarioGuid"),
                rs.getString("nombreCompleto"),
                rs.getString("correo"),
                rs.getTimestamp("fechaCreacion").toLocalDateTime()
            ));
        }
        return lista;
    }
    
    @Override
    public boolean update(vUsuario_Perfil_Global_DTO u) throws Exception {
        String sql = """
            UPDATE vUsuario_Perfil_Global
            SET nombreCompleto = ?, correo = ?
            WHERE UsuarioGuid = ?
        """;
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, u.getNombreCompleto());
        ps.setString(2, u.getCorreo());
        ps.setString(3, u.getUsuarioGuid());
        return ps.executeUpdate() > 0;
    }
    
    @Override
    public boolean delete(Integer id) throws Exception {
        throw new UnsupportedOperationException("Use deleteByGuid(String) instead");
    }
    
    // Método apropiado para eliminar por UsuarioGuid
    public boolean deleteByGuid(String usuarioGuid) throws Exception {
        String sql = "DELETE FROM vUsuario_Perfil_Global WHERE UsuarioGuid = ?";
        Connection conn = openConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, usuarioGuid);
        return ps.executeUpdate() > 0;
    }
}