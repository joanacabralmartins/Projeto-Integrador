package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class JanelaPrincipal extends BaseController {

    @FXML
    private void carregaTelaCadastroCarro(ActionEvent evento) {
        App.changeScreenRegion("CADASTRO CARROS", BorderPaneRegion.CENTER);
    }

}