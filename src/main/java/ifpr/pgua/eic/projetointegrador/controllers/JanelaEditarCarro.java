package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaEditarCarro {
    
    @FXML
    private TextField tfPlaca;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfCor;

    private MotoristaRepository repositorioM;
    private CarroRepository repositorioC;

    
    public JanelaEditarCarro(MotoristaRepository repositorioM, CarroRepository repositorioC) {
        this.repositorioM = repositorioM;
        this.repositorioC = repositorioC;
    }

    @FXML
    private void editarCarro() {

        Carro carro = new Carro(1, tfPlaca.getText(), tfModelo.getText(), tfCor.getText(), repositorioM.getUser().getId(), true);
        
        Result resultado = repositorioC.update(carro);
        
        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult){
            limpar();
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();
    }

    private void limpar() {
        tfPlaca.clear();
        tfModelo.clear();
        tfCor.clear();
    }

}