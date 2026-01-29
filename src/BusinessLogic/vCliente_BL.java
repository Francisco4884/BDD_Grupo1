package BusinessLogic;

import java.util.List;
import DataAccess.vCliente_DAO;
import DataAccess.DTO.vCliente_DTO;

public class vCliente_BL {
    private vCliente_DTO cliente;
    private vCliente_DAO dao = new vCliente_DAO();

    public vCliente_BL() {}

    public List<vCliente_DTO> getAll() throws Exception {
        return dao.readAll();
    }

    public vCliente_DTO getBy(int idCliente) throws Exception {
        cliente = dao.read(idCliente);
        return cliente;
    }

    public vCliente_DTO getByCedula(String cedula) throws Exception {
        cliente = dao.readByCedula(cedula);
        return cliente;
    }

    public vCliente_DTO getByGuid(String clienteGuid) throws Exception {
        cliente = dao.readByGuid(clienteGuid);
        return cliente;
    }

    public boolean add(vCliente_DTO cliente) throws Exception {
        return dao.create(cliente);
    }

    public boolean update(vCliente_DTO cliente) throws Exception {
        return dao.update(cliente);
    }

    public boolean delete(int idCliente) throws Exception {
        return dao.delete(idCliente);
    }
}