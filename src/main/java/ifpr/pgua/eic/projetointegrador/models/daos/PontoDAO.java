package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.PontoParada;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface PontoDAO {

    Result create(PontoParada Ponto);
    Result update(PontoParada Ponto);
    Result delete(PontoParada Ponto);
    List<PontoParada> getByCarona(int id_carona);
    PontoParada getById(int id_ponto);

}
