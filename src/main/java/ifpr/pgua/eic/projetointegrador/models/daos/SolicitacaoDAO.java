package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface SolicitacaoDAO {

  Result create(SolicitacaoCarona Solicitacao);
  Result aceitar(SolicitacaoCarona Solicitacao);
  Result recusar(SolicitacaoCarona Solicitacao);
  Result cancelarSolicitacaoPendente(SolicitacaoCarona Solicitacao);
  Result cancelarSolicitacaoAceita(SolicitacaoCarona Solicitacao);
  Result remover(SolicitacaoCarona Solicitacao);
  List<SolicitacaoCarona> listAll();
  List<SolicitacaoCarona> getByMotorista(int id_motorista);//para tela solicitacao-motorista
  List<SolicitacaoCarona> getByPassageiro(int id_passageiro);//para tela solicitacao-usuario
  List<SolicitacaoCarona> getAceitasByCarona(int id_carona);//para gerenciamento de passageiros
  SolicitacaoCarona getById(int id);

}
