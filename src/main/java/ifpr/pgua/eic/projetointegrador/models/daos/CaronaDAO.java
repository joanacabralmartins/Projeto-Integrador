package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface CaronaDAO {

    Result create(Carona carona);
    Result update(Carona carona);
    Result inativar(Carona carona);
    Carona getCarona();
    void selecionarCarona(Carona carona);
    List<Carona> getByCarro(int id_carro);
    void adicionarLugarDisponivel(int id_carona);
    void subtrairLugarDisponivel(int id_carona);
    List<Carona> getByMotorista(int id_motorista);
    void inativarByMotorista(int id_motorista);
    List<Carona> getByOrigem(String origem);
    List<Carona> getByDestino(String destino);
    List<Carona> getByOrigemAndDestino(String origem, String destino);
    List<Carona> listAll();
    Carona getById(int id);

}
