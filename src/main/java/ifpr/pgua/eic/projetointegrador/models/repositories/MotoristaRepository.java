package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import ifpr.pgua.eic.projetointegrador.models.daos.MotoristaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class MotoristaRepository {
    private List<Motorista> motoristas;
    private MotoristaDAO dao;

    public MotoristaRepository(MotoristaDAO dao) {
        this.dao = dao;

        motoristas = dao.listAll();
    }

    public Result adicionarCarro(String placa, String modelo, String cor, String cpf_motorista) {
        Carro carro = new Carro(placa, modelo, cor, cpf_motorista);
        if(!placa.substring(0, 3).matches("[A-Z]*")) { //verifica se os 3 primeiros caracteres são letras
            return Result.fail("Insira uma placa válida!");
        }
        if(!placa.substring(3).matches("[0-9]*")) { //verifica se os 4 ultimos caracteres sao numeros
            return Result.fail("Insira uma placa válida!"); 
        }
        if(placa.length() != 7 ) {
            return Result.fail("Uma placa deve conter 7 caracteres!");
        }
        
        return dao.adicionarCarro(carro);
    }

    public Result adicionarMotorista(String cpf, String nome, String funcao, String senha, Date dataNascimento,
                                    int idade, String curso, String telefone, String endereco, String carteira) {
        Motorista motorista = new Motorista(cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco, carteira);
        
        Optional<Motorista> busca = motoristas.stream().filter((moto)->moto.getCpf().equals(cpf)).findFirst();
        if (busca.isPresent()) { //verifica se o motorista realmente ainda não tem cadastro
            return Result.fail("Motorista já cadastrado!");
        }
        
        if (cpf == null || nome == null || funcao == null || senha == null || dataNascimento == null || carteira == null) {
            return Result.fail("Preencha todos os campos não opcionais!");
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

    public Result validarLogin(String cpf, String senha) {
        return dao.validarLogin(cpf, senha);
    }

}
