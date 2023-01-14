package ifpr.pgua.eic.projetointegrador.controllers;

import java.util.Optional;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class JanelaPrincipalCaroneiro extends BaseController {

    @FXML
    private void carregaTelaHome(ActionEvent evento) {
        App.pushScreen("PRINCIPAL CARONEIRO");
    }

    @FXML
    private void carregaTelaUsuario(ActionEvent evento) {
        App.changeScreenRegion("USUARIO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaCaronaUsuario(ActionEvent evento) {
        App.changeScreenRegion("CARONA USUARIO TESTE", BorderPaneRegion.CENTER);
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