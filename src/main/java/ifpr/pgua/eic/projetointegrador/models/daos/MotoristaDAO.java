package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface MotoristaDAO {
    Result create(Motorista motorista);
    Result adicionarCarro(Carro carro);
    Result update(String cpf, String cpfNovo, String carteira, String nome, String senha, String curso, String telefone, String endereco);
    Result validarLogin(String cpf, String senha);
    List<Motorista> listAll();
    Motorista getUser();
}
