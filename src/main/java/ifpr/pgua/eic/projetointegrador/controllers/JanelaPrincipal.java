package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class JanelaPrincipal {
    
    @FXML
    private void carregaTelaCadastroCarro(ActionEvent evento){
        App.pushScreen("CADASTRO CARRO");
    }

}