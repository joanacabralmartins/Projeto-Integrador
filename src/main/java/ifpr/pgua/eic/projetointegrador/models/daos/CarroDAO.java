package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface CarroDAO {
    Result create(Carro carro);
    Result update(Carro carro);
    Result inativar(Carro carro);
    List<Carro> listAll();
    List<Carro> listById(int id_motorista);
    void inativarByMotorista(int id_motorista);
    Carro getById(int id);
    Carro getByPlaca(String placa);
    void selecionarCarro(Carro carro);
    Carro getCarro();
}
