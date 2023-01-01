package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface CaronaDAO {

    Result create(Carona carona);
    Result update(Carona carona);
    Result inativar(Carona carona);
    List<Carona> getByMotorista(int id_motorista);
    List<Carona> listAll();
    Carona getById(int id);

}
