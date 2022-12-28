package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.FailResult;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaEditarMotorista implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCPF;

    @FXML
    private TextField tfCarteiraMotorista;

    @FXML
    private TextField tfIdade;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextArea taEndereco;

    private MotoristaRepository repositorio;
    private String cpf;

    public JanelaEditarMotorista(MotoristaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cpf = repositorio.getUser().getCpf();
        String carteira = repositorio.getUser().getCarteira_motorista();
        String nome = repositorio.getUser().getNome();
        String senha = repositorio.getUser().getSenha();
        String curso = repositorio.getUser().getCurso();
        String telefone = repositorio.getUser().getTelefone();
        String endereco = repositorio.getUser().getEndereco();
        
        tfCPF.setText(cpf);
        tfCarteiraMotorista.setText(carteira);
        tfNome.setText(nome);
        tfCurso.setText(curso);
        tfTelefone.setText(telefone);
        taEndereco.setText(endereco);
        pfSenha.setText(senha);
    }

    @FXML
    private void editarMotorista() {
        String cpfNovo = tfCPF.getText();
        String carteira = tfCarteiraMotorista.getText();
        String nome = tfNome.getText();
        String senha = pfSenha.getText();
        String curso = tfCurso.getText();
        String telefone = tfTelefone.getText();
        String endereco = taEndereco.getText();

        Result resultado = repositorio.editarMotorista(cpf, cpfNovo, carteira, nome, senha, curso, telefone, endereco);
        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult) {
            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();
        }

        if(resultado instanceof FailResult) {
            Alert alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        }

    }

}