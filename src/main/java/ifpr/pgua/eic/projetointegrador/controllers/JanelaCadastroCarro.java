package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.FailResult;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;

public class JanelaCadastroCarro implements Initializable {
    
    @FXML
    private TextField tfPlaca;

    @FXML
    private TextField tfQuantidadeLugares;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfCor;

    private MotoristaRepository repositorioM;
    private CarroRepository repositorioC;

    
    public JanelaCadastroCarro(MotoristaRepository repositorioM, CarroRepository repositorioC) {
        this.repositorioM = repositorioM;
        this.repositorioC = repositorioC;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        tfQuantidadeLugares.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("[0-9]*")) ? change : null));
    }

    @FXML
    private void cadastrarCarro() {

        String msg;

        if(tfPlaca.getText().isBlank() || tfModelo.getText().isBlank() || tfQuantidadeLugares.getText().isBlank() || tfCor.getText().isBlank()){
          msg = "Preencha todos os campos!";

          Alert alert = new Alert(AlertType.INFORMATION,msg);
          alert.showAndWait();

          return;
        }

        int lugares = Integer.parseInt(tfQuantidadeLugares.getText());
        
        if(lugares < 1 || lugares > 10){
            msg = "A quantidade de lugares deve ser um número entre 1 e 10!";

            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();

            return;
        }

        Carro carro = new Carro(1, tfPlaca.getText(), tfModelo.getText(), lugares, tfCor.getText(), repositorioM.getUser().getId(), true);
        
        Result resultado = repositorioC.adicionarCarro(carro);
        
        msg = resultado.getMsg();

        if(resultado instanceof SuccessResult){
            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();
            voltar();
        }

        if(resultado instanceof FailResult) {
            Alert alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        }
    }

    @FXML
    private void voltar() {
        App.changeScreenRegion("GERENCIAR CARROS", BorderPaneRegion.CENTER);
    }

}