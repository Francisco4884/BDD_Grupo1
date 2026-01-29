package BusinessLogic;

import java.util.List;
import DataAccess.vSucursal_DAO;
import DataAccess.DTO.vSucursal_DTO;

public class vSucursal_BL {
    private vSucursal_DTO sucursal;
    private vSucursal_DAO dao = new vSucursal_DAO();

    public vSucursal_BL() {}

    public List<vSucursal_DTO> getAll() throws Exception {
        return dao.readAll();
    }

    public vSucursal_DTO getBy(Integer id) throws Exception {
        sucursal = dao.read(id);
        return sucursal;
    }

    public vSucursal_DTO getByCode(String idSucursal) throws Exception {
        sucursal = dao.readByCode(idSucursal);
        return sucursal;
    }

    public boolean add(vSucursal_DTO sucursal) throws Exception {
        return dao.create(sucursal);
    }

    public boolean update(vSucursal_DTO sucursal) throws Exception {
        return dao.update(sucursal);
    }

    public boolean delete(Integer id) throws Exception {
        return dao.delete(id);
    }

    public boolean deleteByCode(String idSucursal) throws Exception {
        return dao.deleteByCode(idSucursal);
    }
}