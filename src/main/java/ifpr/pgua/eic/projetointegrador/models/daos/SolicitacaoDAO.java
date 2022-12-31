package ifpr.pgua.eic.projetointegrador.models.daos;

import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public interface SolicitacaoDAO {

  Result create(SolicitacaoCarona Solicitacao);
  Result aceitar(SolicitacaoCarona Solicitacao);
  Result recusar(SolicitacaoCarona Solicitacao);
  Result cancelar(SolicitacaoCarona Solicitacao);
  Result remover(SolicitacaoCarona Solicitacao);
  List<SolicitacaoCarona> getPendenteByMotorista(int id_motorista);//para tela solicitacao-motorista
  List<SolicitacaoCarona> getPendenteByPassageiro(int id_passageiro);//para tela solicitacao-usuario
  List<SolicitacaoCarona> getAceitasByCarona(int id_carona);//para gerenciamento de passageiros
  SolicitacaoCarona getById(int id);

}