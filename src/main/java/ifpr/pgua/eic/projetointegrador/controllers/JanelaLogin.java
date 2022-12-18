package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class JanelaLogin extends BaseController {
    
    @FXML
    private void carregaTelaOpcaoCadastro(ActionEvent evento) {
        App.changeScreenRegion("OPCAO CADASTRO", BorderPaneRegion.CENTER);
    }

}
