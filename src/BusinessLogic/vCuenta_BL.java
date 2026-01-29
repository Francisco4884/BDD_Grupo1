package BusinessLogic;

import java.util.List;
import DataAccess.vCuenta_DAO;
import DataAccess.DTO.vCuenta_DTO;

public class vCuenta_BL {
    private vCuenta_DTO cuenta;
    private vCuenta_DAO dao = new vCuenta_DAO();

    public vCuenta_BL() {}

    public List<vCuenta_DTO> getAll() throws Exception {
        return dao.readAll();
    }

    public vCuenta_DTO getBy(int idCuenta) throws Exception {
        cuenta = dao.read(idCuenta);
        return cuenta;
    }

    public boolean add(vCuenta_DTO cuenta) throws Exception {
        return dao.create(cuenta);
    }

    public boolean update(vCuenta_DTO cuenta) throws Exception {
        return dao.update(cuenta);
    }

    public boolean delete(int idCuenta) throws Exception {
        return dao.delete(idCuenta);
    }
}