package BusinessLogic;

import java.util.List;
import DataAccess.vUsuario_Completo_DAO;
import DataAccess.DTO.vUsuario_Completo_DTO;

public class vUsuario_Completo_BL {
    private vUsuario_Completo_DTO usuario;
    private vUsuario_Completo_DAO dao = new vUsuario_Completo_DAO();

    public vUsuario_Completo_BL() {}

    public List<vUsuario_Completo_DTO> getAll() throws Exception {
        return dao.readAll();
    }

    public vUsuario_Completo_DTO getBy(Integer id) throws Exception {
        usuario = dao.read(id);
        return usuario;
    }

    public vUsuario_Completo_DTO getByUsername(String username) throws Exception {
        usuario = dao.readByUsername(username);
        return usuario;
    }

    public boolean add(vUsuario_Completo_DTO usuario) throws Exception {
        return dao.create(usuario);
    }

    public boolean update(vUsuario_Completo_DTO usuario) throws Exception {
        return dao.update(usuario);
    }

    public boolean delete(Integer id) throws Exception {
        return dao.delete(id);
    }

    public boolean deleteByGuid(String usuarioGuid) throws Exception {
        return dao.deleteByGuid(usuarioGuid);
    }
}