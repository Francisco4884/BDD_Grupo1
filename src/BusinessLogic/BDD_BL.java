package BusinessLogic;

import java.util.List;

import DataAccess.BDD_DAO;
import DataAccess.DTO.BDD_DTO;

public class BDD_BL {
    private BDD_DTO practica;
    private BDD_DAO dao = new BDD_DAO();

    public BDD_BL(){}

    public List<BDD_DTO> getAll() throws Exception{
        return dao.readAll();
    }

    public BDD_DTO getBy(int idPersonaRol) throws Exception{
        practica = dao.read(idPersonaRol);
        return practica;
    }

    public boolean add(BDD_DTO practica) throws Exception{
        return dao.create(practica);
    }

    public boolean update(BDD_DTO practica) throws Exception{
        return dao.update(practica);
    }

    public boolean delete(int idPersonaRol) throws Exception {
        return dao.delete(idPersonaRol);
    }


}
