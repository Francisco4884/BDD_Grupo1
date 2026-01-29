package BusinessLogic;

import java.util.List;
import DataAccess.vTransaccion_DAO;
import DataAccess.DTO.vTransaccion_DTO;

public class vTransaccion_BL {
    private vTransaccion_DTO transaccion;
    private vTransaccion_DAO dao = new vTransaccion_DAO();

    public vTransaccion_BL() {}

    public List<vTransaccion_DTO> getAll() throws Exception {
        return dao.readAll();
    }

    public vTransaccion_DTO getBy(int idTransaccion) throws Exception {
        transaccion = dao.read(idTransaccion);
        return transaccion;
    }

    public boolean add(vTransaccion_DTO transaccion) throws Exception {
        return dao.create(transaccion);
    }

    public boolean update(vTransaccion_DTO transaccion) throws Exception {
        return dao.update(transaccion);
    }

    public boolean delete(int idTransaccion) throws Exception {
        return dao.delete(idTransaccion);
    }
}