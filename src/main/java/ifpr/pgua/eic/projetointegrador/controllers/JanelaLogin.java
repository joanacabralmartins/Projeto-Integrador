package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class JanelaLogin extends BaseController {
    
    @FXML
    private void carregaTelaOpcaoCadastro(ActionEvent evento) {
        App.pushScreen("OPCAO CADASTRO");
    }

}
