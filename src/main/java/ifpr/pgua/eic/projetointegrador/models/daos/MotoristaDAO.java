package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface MotoristaDAO {
    Result create(Motorista motorista);
    Result adicionarCarro(Carro carro);
    List<Motorista> listAll();
}
