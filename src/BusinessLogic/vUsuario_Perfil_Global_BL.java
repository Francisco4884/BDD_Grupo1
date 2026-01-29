package BusinessLogic;

import java.util.List;
import DataAccess.vUsuario_Perfil_Global_DAO;
import DataAccess.DTO.vUsuario_Perfil_Global_DTO;

public class vUsuario_Perfil_Global_BL {
    private vUsuario_Perfil_Global_DTO usuario;
    private vUsuario_Perfil_Global_DAO dao = new vUsuario_Perfil_Global_DAO();

    public vUsuario_Perfil_Global_BL() {}

    public List<vUsuario_Perfil_Global_DTO> getAll() throws Exception {
        return dao.readAll();
    }

    public vUsuario_Perfil_Global_DTO getBy(Integer id) throws Exception {
        usuario = dao.read(id);
        return usuario;
    }

    public vUsuario_Perfil_Global_DTO getByGuid(String usuarioGuid) throws Exception {
        usuario = dao.readByGuid(usuarioGuid);
        return usuario;
    }

    public List<vUsuario_Perfil_Global_DTO> getBySucursal(String idSucursal) throws Exception {
        return dao.readBySucursal(idSucursal);
    }

    public boolean add(vUsuario_Perfil_Global_DTO usuario) throws Exception {
        return dao.create(usuario);
    }

    public boolean update(vUsuario_Perfil_Global_DTO usuario) throws Exception {
        return dao.update(usuario);
    }

    public boolean delete(Integer id) throws Exception {
        return dao.delete(id);
    }

    public boolean deleteByGuid(String usuarioGuid) throws Exception {
        return dao.deleteByGuid(usuarioGuid);
    }
}