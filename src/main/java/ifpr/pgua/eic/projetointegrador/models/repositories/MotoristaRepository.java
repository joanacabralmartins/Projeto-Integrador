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

    public Result adicionarMotorista(int ativo, String cpf, String nome, String funcao, String senha, Date dataNascimento,
                                    int idade, String curso, String telefone, String endereco, String carteira) {
        Motorista motorista = new Motorista(ativo, cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco, carteira);
        
        Optional<Motorista> busca = motoristas.stream().filter((moto)->moto.getCpf().equals(cpf)).findFirst();
        if (busca.isPresent()) { //verifica se o motorista realmente ainda não tem cadastro
            return Result.fail("Motorista já cadastrado!");
        }

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

        return dao.create(motorista);
    }

    public Result editarMotorista(String cpf, String cpfNovo, String carteira, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {
        return dao.update(cpf, cpfNovo, carteira, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
    }

    public Result validarLogin(String cpf, String senha) {
        return dao.validarLogin(cpf, senha);
    }

    public Motorista getUser() {
        return dao.getUser();
    }

}
