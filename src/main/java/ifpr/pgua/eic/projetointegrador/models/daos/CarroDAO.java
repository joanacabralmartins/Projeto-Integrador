package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface CarroDAO {
    Result create(Carro carro);
    List<Carro> listAll(String cpfMotorista);
}
