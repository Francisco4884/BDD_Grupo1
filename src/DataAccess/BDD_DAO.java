package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DataAccess.DTO.BDD_DTO;

public class BDD_DAO extends SQLDataHelper implements IDAO<BDD_DTO>{

    @Override
    public boolean create(BDD_DTO entity) throws Exception {
        String query = "INSERT INTO PersonaRol (Nombre, Observacion) VALUES (?, ?)";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getObservacion());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<BDD_DTO> readAll() throws Exception {
        List<BDD_DTO> list = new ArrayList<>();
        String query = "SELECT idPersonaRol, Nombre, Observacion, Estado, FechaCrea, FechaModifica FROM PersonaRol where Estado = 'A'";
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                BDD_DTO s = new BDD_DTO(rs.getInt(1)
                                                ,rs.getString(2)
                                                ,rs.getString(3)
                                                ,rs.getString(4)
                                                ,rs.getString(5)
                                                ,rs.getString(6)
                                                );
                list.add(s);
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public BDD_DTO read(Integer id) throws Exception {
        BDD_DTO oS = new BDD_DTO();
        String query = "SELECT idPersonaRol, Nombre, Observacion, Estado, FechaCrea, FechaModifica FROM PersonaRol where Estado = 'A' AND idPersonaRol = "+ id.toString();
        try {
            Connection conn = openConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs   = stmt.executeQuery(query);
            while (rs.next()) {
                oS = new BDD_DTO(rs.getInt(1)
                                                ,rs.getString(2)
                                                ,rs.getString(3)
                                                ,rs.getString(4)
                                                ,rs.getString(5)
                                                ,rs.getString(6)
                                                );
            }
        } catch (Exception e) {
            throw e;
        }
        return oS;
    }

    @Override
    public boolean update(BDD_DTO entity) throws Exception {
        DateTimeFormatter date = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String query = "UPDATE PersonaRol SET Nombre = ?, Observacion = ?, FechaModifica = ? WHERE idPersonaRol = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, entity.getNombre());
            pstmt.setString(2, entity.getObservacion());
            pstmt.setString(3, date.format(now).toString());
            pstmt.setInt(4, entity.getIdPersonaRol());
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        String query = "UPDATE PersonaRol SET Estado = ? WHERE idPersonaRol = ?";
        try {
            Connection conn = openConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "X");
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}