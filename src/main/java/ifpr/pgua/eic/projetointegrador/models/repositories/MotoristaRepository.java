package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class MotoristaRepository {
    private List<Motorista> motoristas;
    private MotoristaDAO dao;

    public MotoristaRepository(MotoristaDAO dao) {
        this.dao = dao;

        motoristas = dao.listAll();
    }

    public Result adicionarMotorista(boolean ativo, String cpf, String nome, String funcao, String senha, Date dataNascimento,
    int idade, String curso, String telefone, String endereco, String carteira_motorista) {
        Motorista motorista = new Motorista(0, ativo, cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco, carteira_motorista);
        
        if (cpf.length() != 11) {
            return Result.fail("o CPF inserido não possui 11 caracteres!");
        }
        if(carteira_motorista.length() < 11){
            return Result.fail("Carteira de motorista incorreta");
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

        return dao.create(motorista);
    }

    public Result editarMotorista(String cpf, String cpfNovo, String carteira, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {
        Optional<Motorista> busca = motoristas.stream().filter((moto)->moto.getCpf().equals(cpfNovo)).findFirst();
        
        if(busca.isPresent()){
            return Result.fail("O CPF digitado já foi cadastrado!");
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
