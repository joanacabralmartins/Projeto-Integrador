package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.daos.CarroDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;

public class CarroRepository {

    private CarroDAO dao;
    
    public CarroRepository(CarroDAO dao) {
        this.dao = dao;
    }

    public List<Carro> listAll(String cpfMotorista) {
        return dao.listAll(cpfMotorista);
    }
}
