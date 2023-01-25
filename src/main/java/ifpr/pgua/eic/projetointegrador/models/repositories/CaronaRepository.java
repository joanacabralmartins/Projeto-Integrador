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

  public Carona getCarona(){
    return dao.getCarona();
  }

  public void selecionarCarona(Carona carona) {
    dao.selecionarCarona(carona);
  }

  public List<Carona> getByCarro(int id_carro){
    return dao.getByCarro(id_carro);
  }

  public void adicionarLugarDisponivel(int id_carona) {
    dao.adicionarLugarDisponivel(id_carona);
  }

  public void subtrairLugarDisponivel(int id_carona) {
    dao.subtrairLugarDisponivel(id_carona);
  }

  public List<Carona> getByMotorista(int id){
    return dao.getByMotorista(id);
  }

  public void inativarByMotorista(int id_motorista){
    dao.inativarByMotorista(id_motorista);
  }

  public void inativarByCarro(int id_carro){
    dao.inativarByCarro(id_carro);
  }

  public List<Carona> getByDestino(String destino){
    return dao.getByDestino(destino);
  }

  public List<Carona> getByOrigem(String origem){
    return dao.getByOrigem(origem);
  }

  public List<Carona> getByOrigemAndDestino(String origem, String destino){
    return dao.getByOrigemAndDestino(origem, destino);
  }

  public List<Carona> listAll() {
    return dao.listAll();
  }

  public Carona getById(int id) {
    return dao.getById(id);
  }
  
}
