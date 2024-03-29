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

  public Result aceitar(SolicitacaoCarona solicitacao) {
    return dao.aceitar(solicitacao);
  }

  public Result recusar(SolicitacaoCarona Solicitacao) {
    if(Solicitacao.getStatus().matches("Recusada")) {
      return Result.fail("Essa solicitação já foi recusada!");
    }
    if(Solicitacao.getStatus().matches("Cancelada")) {
      return Result.fail("Essa solicitação foi cancelada!");
    }

    return dao.recusar(Solicitacao);
  }

  public Result cancelar(SolicitacaoCarona Solicitacao) {
    if(Solicitacao.getStatus().matches("Recusada")) {
      return Result.fail("Essa solicitação já foi recusada!");
    }
    if(Solicitacao.getStatus().matches("Cancelada")) {
      return Result.fail("Essa solicitação já foi cancelada!");
    }

    if(Solicitacao.getStatus().matches("Pendente")) {
      return dao.cancelarSolicitacaoPendente(Solicitacao);
    }
    
    return dao.cancelarSolicitacaoAceita(Solicitacao);
    
  }

  public Result remover(SolicitacaoCarona Solicitacao) {
    return dao.remover(Solicitacao);
  }

  public List<SolicitacaoCarona> getByMotorista(int id_motorista) {
    return dao.getByMotorista(id_motorista);
  }

  public List<SolicitacaoCarona> getByPassageiro(int id_passageiro) {
    return dao.getByPassageiro(id_passageiro);
  }

  public List<SolicitacaoCarona> getAceitasByCarona(int id_carona) {
    return dao.getAceitasByCarona(id_carona);
  }

  public SolicitacaoCarona getById(int id) {
    return dao.getById(id);
  }
  
}
