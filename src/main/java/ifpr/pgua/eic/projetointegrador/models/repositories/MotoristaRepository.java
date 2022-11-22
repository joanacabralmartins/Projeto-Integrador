package ifpr.pgua.eic.projetointegrador.models.repositories;

import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class MotoristaRepository {
    private MotoristaDAO dao;

    public MotoristaRepository(MotoristaDAO dao) {
        this.dao = dao;
    }

    public Result adicionarCarro(String placa, String modelo, String cor, String cpf_motorista) {
        Carro carro = new Carro(placa, modelo, cor, cpf_motorista);
        return dao.adicionarCarro(carro);
    }

}