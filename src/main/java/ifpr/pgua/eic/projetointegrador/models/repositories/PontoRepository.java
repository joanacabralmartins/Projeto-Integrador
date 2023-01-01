package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.daos.PontoDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.PontoParada;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class PontoRepository {

  PontoDAO dao;

  public PontoRepository(PontoDAO dao) {
    this.dao = dao;
  }

  public Result create(PontoParada ponto) {
    return dao.create(ponto);
  }

  public Result update(PontoParada ponto) {
    return dao.update(ponto);
  }

  public Result inativar(PontoParada ponto) {
    return dao.inativar(ponto);
  }

  public PontoParada getById(int id_ponto) {
    return dao.getById(id_ponto);
  }

  public List<PontoParada> getByCarona(int id_carona) {
    return dao.getByCarona(id_carona);
  }
  
}
