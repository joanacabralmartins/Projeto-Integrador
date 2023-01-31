package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface SolicitacaoDAO {

  Result create(SolicitacaoCarona solicitacao);
  Result aceitar(SolicitacaoCarona solicitacao);
  Result recusar(SolicitacaoCarona solicitacao);
  Result cancelarSolicitacaoPendente(SolicitacaoCarona solicitacao);
  Result cancelarSolicitacaoAceita(SolicitacaoCarona solicitacao);
  Result remover(SolicitacaoCarona solicitacao);
  List<SolicitacaoCarona> listAll();
  List<SolicitacaoCarona> getByMotorista(int id_motorista);//para tela solicitacao-motorista
  List<SolicitacaoCarona> getByPassageiro(int id_passageiro);//para tela solicitacao-usuario
  List<SolicitacaoCarona> getAceitasByCarona(int id_carona);//para gerenciamento de passageiros
  SolicitacaoCarona getById(int id);

}
