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

    public Result adicionarMotorista(Motorista motorista) {
        
        Optional<Motorista> busca = motoristas.stream().filter((moto)->moto.getCpf().equals(motorista.getCpf())).findFirst();
        if (busca.isPresent()) { //verifica se o motorista realmente ainda não tem cadastro
            return Result.fail("Motorista já cadastrado!");
        }

        if (motorista.getCpf().length() < 11) {
            return Result.fail("Insira um CPF válido!");
        }
        if (motorista.getCpf().matches("[a-z]*")) {
            return Result.fail("Insira um CPF válido!");
        }
        if(motorista.getCpf().matches("[A-Z]*")){
            return Result.fail("Insira um CPF válido!");
        }
        if(motorista.getCarteira_motorista().length() < 11){
            return Result.fail("Carteira de motorista incorreta");
        }
        if (motorista.getIdade() < 18) {
            return Result.fail("É necessário ter pelo menos 18 anos para se cadastrar!");
        }

        return dao.create(motorista);
    }

    public Result editarMotorista(String cpf, String cpfNovo, String carteira, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {
        return dao.update(cpf, cpfNovo, carteira, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
    }

    public Result validarLogin(String cpf, String senha) {
        return dao.validarLogin(cpf, senha);
    }

    public Result inativar(Motorista motorista){
        return dao.inativar(motorista);
    }

    public Motorista getUser() {
        return dao.getUser();
    }

}
