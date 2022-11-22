package ifpr.pgua.eic.projetointegrador.models.daos;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface MotoristaDAO {
    Result adicionarCarro(Carro carro);
}
