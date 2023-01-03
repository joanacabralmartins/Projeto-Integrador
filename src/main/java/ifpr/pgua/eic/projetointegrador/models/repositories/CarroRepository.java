package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.daos.CarroDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class CarroRepository {

    private CarroDAO dao;
    
    public CarroRepository(CarroDAO dao) {
        this.dao = dao;
    }

    public Result adicionarCarro(Carro carro) {
        if(!carro.getPlaca().substring(0, 3).matches("[A-Z]*")) { //verifica se os 3 primeiros caracteres são letras
            return Result.fail("Insira uma placa válida!");
        }
        if(!carro.getPlaca().substring(3).matches("[0-9]*")) { //verifica se os 4 ultimos caracteres sao numeros
            return Result.fail("Insira uma placa válida!"); 
        }
        if(carro.getPlaca().length() != 7 ) {
            return Result.fail("Uma placa deve conter 7 caracteres!");
        }
        
        return dao.create(carro);
    }

    public Result update(Carro carro) {
        return dao.update(carro);
    }

    public Result inativar(Carro carro){
        return dao.inativar(carro);
    }

    public List<Carro> listar(int id_motorista) {
        return dao.listAll(id_motorista);
    }
}