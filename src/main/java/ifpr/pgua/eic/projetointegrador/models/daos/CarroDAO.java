package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;

public interface CarroDAO {
    List<Carro> listAll(String cpfMotorista);
}
