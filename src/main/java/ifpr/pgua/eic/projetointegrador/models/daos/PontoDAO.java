package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.PontoParada;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface PontoDAO {

    Result create(PontoParada ponto);
    Result update(PontoParada ponto);
    Result inativar(PontoParada ponto);
    List<PontoParada> getByCarona(int id_carona);
    PontoParada getById(int id_ponto);

}
