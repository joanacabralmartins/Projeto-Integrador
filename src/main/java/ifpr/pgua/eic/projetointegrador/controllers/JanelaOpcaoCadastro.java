package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class JanelaOpcaoCadastro {

    @FXML
    private void carregaTelaCadastroUsuario(ActionEvent evento) {
        App.changeScreenRegion("CADASTRO USUARIO", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaCadastroMotorista(ActionEvent evento) {
        App.changeScreenRegion("CADASTRO MOTORISTA", BorderPaneRegion.CENTER);
    }
}
