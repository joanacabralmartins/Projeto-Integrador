package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Date;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface MotoristaDAO {
    Result create(Motorista motorista);
    Result update(String cpf, String cpfNovo, String carteira, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco);
    Result validarLogin(String cpf, String senha);
    Result inativar(Motorista motorista);
    List<Motorista> listAll();
    Motorista getUser();
}
