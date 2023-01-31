package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.sql.Date;

import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class MotoristaRepository {
    private MotoristaDAO dao;

    public MotoristaRepository(MotoristaDAO dao) {
        this.dao = dao;
    }

    public Result adicionarMotorista(int id, boolean ativo, String cpf, String nome, String funcao, String senha, Date dataNascimento,
    int idade, String curso, String telefone, String endereco, String carteira_motorista) {

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

        Motorista motorista = new Motorista(id, ativo, cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco, carteira_motorista);

        return dao.create(motorista);
        
    }

    public Result editarMotorista(String cpf, String cpfNovo, String carteira, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {
        
        if (cpf.length() != 11) {
            return Result.fail("Um CPF deve conter 11 caracteres!");
        }
        if (cpf.matches("[a-z]*")) {
            return Result.fail("Insira um CPF válido!");
        }
        if(cpf.matches("[A-Z]*")){
            return Result.fail("Insira um CPF válido!");
        }

        if(carteira.length() != 11){
            return Result.fail("O número da carteira de motorista inserida não possui 11 caracteres!");
        }
        if (carteira.matches("[a-z]*")) {
            return Result.fail("Insira um número de CNH válido! [*Apenas Números*]");
        }
        if(carteira.matches("[A-Z]*")){
            return Result.fail("Insira um número de CNH válido! [*Apenas Números*]");
        }

        if (idade < 18) {
            return Result.fail("É necessário ter pelo menos 18 anos para se cadastrar!");
        }
        
        if (getByCpf(cpfNovo) != null && !cpfNovo.matches(cpf)) {
            return Result.fail("Motorista já cadastrado!");
        }
        
        return dao.update(cpf, cpfNovo, carteira, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
    }

    public Result validarLogin(String cpf, String senha) {
        return dao.validarLogin(cpf, senha);
    }

    public Motorista getByCpf(String cpf){
        return dao.getByCpf(cpf);
    }

    public Motorista getById(int id) {
        return dao.getById(id);
    }

    public Result inativar(Motorista motorista){
        return dao.inativar(motorista);
    }

    public Motorista getUser() {
        return dao.getUser();
    }

}
