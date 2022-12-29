package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.sql.Date;

import ifpr.pgua.eic.projetointegrador.models.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class UsuarioRepository {
    private UsuarioDAO dao;

    public UsuarioRepository(UsuarioDAO dao) {
        this.dao = dao;
    }

    public Result adicionarUsuario(String cpf, String nome, String funcao, String senha, Date dataNascimento,
                                    int idade, String curso, String telefone, String endereco) {
        Usuario caroneiro = new Usuario(cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
        
        if (cpf.length() < 11) {
            return Result.fail("Insira um CPF válido!");
        }
        if (cpf.matches("[a-z]*")) {
            return Result.fail("Insira um CPF válido!");
        }
        if(cpf.matches("[A-Z]*")){
            return Result.fail("Insira um CPF válido!");
        }

        if (idade < 18) {
            return Result.fail("É necessário ter pelo menos 18 anos para se cadastrar!");
        }

        return dao.create(caroneiro);
    }

    public Result editarUsuario(String cpf, String cpfNovo, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {
        return dao.update(cpf, cpfNovo, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
    }

    public Result validarLogin(String cpf, String senha) {
        return dao.validarLogin(cpf, senha);
    }

    public Usuario getUser() {
        return dao.getUser();
    }
    
}
