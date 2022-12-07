package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
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
    private TextField tfModelo;

    @FXML
    private TextField tfCor;

    @FXML
    private TextField tfCPFMotorista;

    private MotoristaRepository repositorio;

    
    public JanelaCadastroCarro(MotoristaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @FXML
    private void cadastrarCarro() {
        String placa = tfPlaca.getText();
        String modelo = tfModelo.getText();
        String cor = tfCor.getText();
        String cpf_motorista = tfCPFMotorista.getText();
        
        Result resultado = repositorio.adicionarCarro(placa, modelo, cor, cpf_motorista);
        
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
        tfCPFMotorista.clear();
    }

}