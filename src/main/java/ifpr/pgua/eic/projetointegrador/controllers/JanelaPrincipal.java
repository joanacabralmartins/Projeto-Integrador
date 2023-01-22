package ifpr.pgua.eic.projetointegrador.controllers;

import java.util.Optional;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class JanelaPrincipal extends BaseController {

    @FXML
    private void carregaTelaHome(ActionEvent evento) {
        App.pushScreen("PRINCIPAL MOTORISTA");
    }

    @FXML
    private void carregaTelaCarro(ActionEvent evento) {
        App.changeScreenRegion("CARROS", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaUsuario(ActionEvent evento) {
        App.changeScreenRegion("USUARIO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaCaronaInicial(ActionEvent evento) {
        App.changeScreenRegion("CARONA MOTORISTA INICIAL", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaSolicitacoesInicial(ActionEvent evento) {
        App.changeScreenRegion("SOLICITACOES MOTORISTA INICIAL", BorderPaneRegion.CENTER);
    }

    @FXML
    private void deslogar(ActionEvent evento) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja sair?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            carregaTelaLogin();
        }
    }

    private void carregaTelaLogin() {
        App.pushScreen("LOGIN");
    }
    
}