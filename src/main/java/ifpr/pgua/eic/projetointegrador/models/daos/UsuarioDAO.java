package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Date;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface UsuarioDAO {
    Result create(Usuario caroneiro);
    Result update(String cpf, String cpfNovo, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco);
    Result validarLogin(String cpf, String senha);
    Result inativar(Usuario caroneiro);
    List<Usuario> listAll();
    Usuario getByCpf(String cpf);
    Usuario getById(int id);
    Usuario getUser();
}
