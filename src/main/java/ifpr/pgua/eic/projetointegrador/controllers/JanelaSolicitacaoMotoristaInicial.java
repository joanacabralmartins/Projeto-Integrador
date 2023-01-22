package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class JanelaSolicitacaoMotoristaInicial {
    
    @FXML
    private void carregaTelaGerenciarSolicitacoes(ActionEvent evento) {
        App.changeScreenRegion("SOLICITACOES MOTORISTA", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaVisualizarSolicitacoes(ActionEvent evento) {
        App.changeScreenRegion("SOLICITACOES MOTORISTA", BorderPaneRegion.CENTER);
    }

}
