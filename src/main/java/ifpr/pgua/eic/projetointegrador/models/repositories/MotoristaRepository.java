package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.sql.Date;

import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
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

    public Result adicionarMotorista(String cpf, String nome, String funcao, String senha, Date dataNascimento,
                                    int idade, String curso, int telefone, String endereco, int carteira) {
        Motorista motorista = new Motorista(cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco, carteira);
        return dao.create(motorista);
    }

}
