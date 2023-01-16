package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class JanelaCaronaMotoristaInicial {

    @FXML
    private void carregaTelaGerenciarCaronas(ActionEvent evento) {
        App.changeScreenRegion("CARONA MOTORISTA", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaVisualizarCaronas(ActionEvent evento) {
        App.changeScreenRegion("CARONA USUARIO", BorderPaneRegion.CENTER);
    }

}
