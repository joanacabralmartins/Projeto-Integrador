package ifpr.pgua.eic.projetointegrador.models.daos;

import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface UsuarioDAO {
    Result create(Usuario caroneiro);
    Result update(String cpf, String cpfNovo, String nome, String senha, String curso, String telefone, String endereco);
    Result validarLogin(String cpf, String senha);
    Usuario getUser();
}
