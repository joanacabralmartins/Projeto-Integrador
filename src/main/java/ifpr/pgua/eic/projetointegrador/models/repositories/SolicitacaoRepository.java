package ifpr.pgua.eic.projetointegrador.models.repositories;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.daos.SolicitacaoDAO;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class SolicitacaoRepository {

  SolicitacaoDAO dao;

  public SolicitacaoRepository(SolicitacaoDAO dao) {
    this.dao = dao;
  }

  public Result create(SolicitacaoCarona Solicitacao) {
    return dao.create(Solicitacao);
  }

  public List<SolicitacaoCarona> listAll() {
    return dao.listAll();
  }

  public Result aceitar(SolicitacaoCarona Solicitacao) {
    return dao.aceitar(Solicitacao);
  }

  public Result recusar(SolicitacaoCarona Solicitacao) {
    return dao.recusar(Solicitacao);
  }

  public Result cancelar(SolicitacaoCarona Solicitacao) {
    return dao.cancelar(Solicitacao);
  }

  public Result remover(SolicitacaoCarona Solicitacao) {
    return dao.remover(Solicitacao);
  }

  public List<SolicitacaoCarona> getPendenteByMotorista(int id_motorista) {
    return dao.getPendenteByMotorista(id_motorista);
  }

  public List<SolicitacaoCarona> getPendenteByPassageiro(int id_passageiro) {
    return dao.getPendenteByPassageiro(id_passageiro);
  }

  public List<SolicitacaoCarona> getAceitasByCarona(int id_carona) {
    return dao.getAceitasByCarona(id_carona);
  }

  public SolicitacaoCarona getById(int id) {
    return dao.getById(id);
  }
  
}
