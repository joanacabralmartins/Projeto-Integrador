package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaEditarCarro implements Initializable {
    
    @FXML
    private TextField tfPlaca;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfCor;

    private MotoristaRepository repositorioM;
    private CarroRepository repositorioC;
    private Carro carro;

    
    public JanelaEditarCarro(MotoristaRepository repositorioM, CarroRepository repositorioC) {
        this.repositorioM = repositorioM;
        this.repositorioC = repositorioC;
        carro = repositorioC.getCarro();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      tfPlaca.setText(carro.getPlaca());
      tfModelo.setText(carro.getModelo());
      tfCor.setText(carro.getCor()); 
    }

    @FXML
    private void editarCarro() {

        carro = new Carro(repositorioC.getCarro().getId(), tfPlaca.getText(), tfModelo.getText(), tfCor.getText(), repositorioM.getUser().getId(), true);
        
        Result resultado = repositorioC.update(carro);
        
        String msg = resultado.getMsg();

        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();

        if(resultado instanceof SuccessResult){
          App.changeScreenRegion("GERENCIAR CARROS", BorderPaneRegion.CENTER);
        }

    }

    @FXML
    private void voltar() {
      App.changeScreenRegion("GERENCIAR CARROS", BorderPaneRegion.CENTER);
    }

    private void limpar() {
        tfPlaca.clear();
        tfModelo.clear();
        tfCor.clear();
    }

}