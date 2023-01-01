package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.daos.CaronaDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class CaronaRepository {

  CaronaDAO dao;

  public CaronaRepository(CaronaDAO dao) {
    this.dao = dao;
  }

  public Result create(Carona carona) {
    return dao.create(carona);
  }

  public Result update(Carona carona) {
    return dao.update(carona);
  }

  public Result inativar(Carona carona) {
    return dao.inativar(carona);
  }

  public List<Carona> getByMotorista(int id_motorista){
    return dao.getByMotorista(id_motorista);
  }

  public List<Carona> listAll() {
    return dao.listAll();
  }

  public Carona getById(int id) {
    return dao.getById(id);
  }
  
}
