package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import com.mysql.cj.CacheAdapter;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class JanelaEditarCarro implements Initializable {
    
    @FXML
    private TextField tfPlaca;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfQuantidadeLugares;

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
      tfQuantidadeLugares.setText(String.valueOf(carro.getQuantidadeLugares()));
      tfCor.setText(carro.getCor()); 
    }

    @FXML
    private void editarCarro() {

        int lugares = Integer.parseInt(tfQuantidadeLugares.getText());

        carro = new Carro(repositorioC.getCarro().getId(), tfPlaca.getText(), tfModelo.getText(), lugares ,tfCor.getText(), repositorioM.getUser().getId(), true);
        
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