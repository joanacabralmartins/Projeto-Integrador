package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.projetointegrador.models.daos.UsuarioDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class UsuarioRepository {
    private List<Usuario> usuarios;
    private UsuarioDAO dao;

    public UsuarioRepository(UsuarioDAO dao) {
        this.dao = dao;

        usuarios = dao.listAll();
    }

    public Result adicionarUsuario(boolean ativo, String cpf, String nome, String funcao, String senha, Date dataNascimento,
                                    int idade, String curso, String telefone, String endereco) {
        Usuario caroneiro = new Usuario(0, ativo, cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);

        if (cpf.length() != 11) {
            return Result.fail("Um CPF deve conter 11 caracteres!");
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
        if (getByCpf(cpf) != null) {
            return Result.fail("Usuário já cadastrado!");
        }

        return dao.create(caroneiro);
    }

    public Result editarUsuario(String cpf, String cpfNovo, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {

        if (cpf.length() != 11) {
            return Result.fail("Um CPF deve conter 11 caracteres!");
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
        
        if (getByCpf(cpfNovo) != null && !cpfNovo.matches(cpf)) {
            return Result.fail("Usuário já cadastrado!");
        }

        return dao.update(cpf, cpfNovo, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
    }

    public Result validarLogin(String cpf, String senha) {
        return dao.validarLogin(cpf, senha);
    }

    public Usuario getByCpf(String cpf){
        return dao.getByCpf(cpf);
    }

    public Usuario getById(int id){
        return dao.getById(id);
    }

    public Result inativar(Usuario usuario){
        return dao.inativar(usuario);
    }

    public Usuario getUser() {
        return dao.getUser();
    }
    
}
