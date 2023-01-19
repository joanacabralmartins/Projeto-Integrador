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

        if(carro.getPlaca().length() != 7 ) {
            return Result.fail("Uma placa deve conter 7 caracteres!");
        }

        if(getByPlaca(carro.getPlaca()) != null){
            return Result.fail("Placa já cadastrada!");
        }
        
        if(!carro.getPlaca().substring(0, 3).matches("[A-Z]*") && !carro.getPlaca().substring(0, 4).matches("[A-Z]*")) { //verifica se os 3 ou 4 primeiros caracteres são letras
            return Result.fail("Uma placa deve conter 3 ou 4 letras entre seus primeiros caracteres!");
        }
        if(!carro.getPlaca().substring(3).matches("[0-9]*") && !carro.getPlaca().substring(4).matches("[0-9]*")) { //verifica se os 3 ou 4 ultimos caracteres sao numeros
            return Result.fail("Uma placa deve conter 3 ou 4 numeros entre seus ultimos caracteres!"); 
        }
        
        return dao.create(carro);
    }

    public Result update(Carro carro) {

        if(carro.getPlaca().length() != 7 ) {
            return Result.fail("Uma placa deve conter 7 caracteres!");
        }

        if(getByPlaca(carro.getPlaca()) != null && !getCarro().getPlaca().matches(carro.getPlaca())){
            return Result.fail("Placa já cadastrada!");
        }
        
        if(!carro.getPlaca().substring(0, 3).matches("[A-Z]*") && !carro.getPlaca().substring(0, 4).matches("[A-Z]*")) {
            return Result.fail("Uma placa deve conter 3 ou 4 letras entre seus primeiros caracteres!");
        }
        if(!carro.getPlaca().substring(3).matches("[0-9]*") && !carro.getPlaca().substring(4).matches("[0-9]*")) {
            return Result.fail("Uma placa deve conter 3 ou 4 numeros entre seus ultimos caracteres!"); 
        }

        return dao.update(carro);
    }

    public Result inativar(Carro carro){
        return dao.inativar(carro);
    }

    public void selecionarCarro(Carro carro){
        dao.selecionarCarro(carro);
    }

    public List<Carro> listar(int id_motorista) {
        return dao.listById(id_motorista);
    }

    public void inativarByMotorista(int id_motorista){
        dao.inativarByMotorista(id_motorista);
    }

    public Carro getById(int id){
        return dao.getById(id);
    }

    public Carro getByPlaca(String placa){
        return dao.getByPlaca(placa);
    }

    public Carro getCarro(){
        return dao.getCarro();
    }
}
