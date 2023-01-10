package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.FailResult;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaCadastroCarro {
    
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

    @FXML
    private void cadastrarCarro() {

        int lugares = Integer.parseInt(tfQuantidadeLugares.getText());

        Carro carro = new Carro(1, tfPlaca.getText(), tfModelo.getText(), lugares, tfCor.getText(), repositorioM.getUser().getId(), true);
        
        Result resultado = repositorioC.adicionarCarro(carro);
        
        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult){
            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();
            limpar();
        }

        if(resultado instanceof FailResult) {
            Alert alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        }
    }

    private void limpar() {
        tfPlaca.clear();
        tfModelo.clear();
        tfQuantidadeLugares.clear();
        tfCor.clear();
    }

}