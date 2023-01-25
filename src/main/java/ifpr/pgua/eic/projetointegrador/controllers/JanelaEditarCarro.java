package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.control.TextFormatter;

public class JanelaEditarCarro implements Initializable {
    
    @FXML
    private TextField tfPlaca;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfQuantidadeLugares;

    @FXML
    private TextField tfCor;

    private MotoristaRepository repositorioMotorista;
    private CarroRepository repositorioCarro;
    private Carro carro;

    
    public JanelaEditarCarro(MotoristaRepository repositorioMotorista, CarroRepository repositorioCarro) {
        this.repositorioMotorista = repositorioMotorista;
        this.repositorioCarro = repositorioCarro;
        carro = repositorioCarro.getCarro();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      tfPlaca.setText(carro.getPlaca());
      tfModelo.setText(carro.getModelo());
      tfQuantidadeLugares.setText(String.valueOf(carro.getQuantidadeLugares()));
      tfCor.setText(carro.getCor()); 
      tfQuantidadeLugares.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
    }

    @FXML
    private void editarCarro() {

        String msg;

        if(tfPlaca.getText().isBlank() || tfModelo.getText().isBlank() || tfQuantidadeLugares.getText().isBlank() || tfCor.getText().isBlank()){
          msg = "Preencha todos os campos!";

          Alert alert = new Alert(AlertType.INFORMATION,msg);
          alert.showAndWait();

          return;
        }

        int lugares = Integer.parseInt(tfQuantidadeLugares.getText());

        if(lugares < 1 || lugares > 99){
          msg = "A quantidade de lugares deve ser um número entre 1 e 99!";

          Alert alert = new Alert(AlertType.INFORMATION,msg);
          alert.showAndWait();

          return;
        }

        carro = new Carro(repositorioCarro.getCarro().getId(), tfPlaca.getText(), tfModelo.getText(), lugares ,tfCor.getText(), repositorioMotorista.getUser().getId(), true);
        
        Result resultado = repositorioCarro.update(carro);
        
        msg = resultado.getMsg();

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

}