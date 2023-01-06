package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface CarroDAO {
    Result create(Carro carro);
    Result update(Carro carro);
    Result inativar(Carro carro);
    List<Carro> listAll(int id_motorista);
    Carro getById(int id);
    void selecionarCarro(Carro carro);
    Carro getCarro();
}
