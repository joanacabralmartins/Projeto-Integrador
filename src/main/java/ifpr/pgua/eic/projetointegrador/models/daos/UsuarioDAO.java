package ifpr.pgua.eic.projetointegrador.models.daos;

import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface UsuarioDAO {
    Result create(Usuario caroneiro);
    Result validarLogin(String cpf, String senha);
}
