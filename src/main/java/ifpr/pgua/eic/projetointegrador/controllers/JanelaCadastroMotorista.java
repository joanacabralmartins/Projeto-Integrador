package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaCadastroMotorista implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCPF;

    @FXML
    private PasswordField pfSenha;

    @FXML 
    private ChoiceBox cbFuncao;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private TextField tfCarteiraMotorista;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextArea taEndereco;

    private MotoristaRepository repositorio;

    public JanelaCadastroMotorista(MotoristaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] opcoes = {"Aluno(a)", "Professor(a)", "Outros"};
        ObservableList<String> list = FXCollections.observableArrayList(opcoes);
        cbFuncao.setItems(list);
    }

    @FXML
    private void cadastrarMotorista() {
        String cpf = tfCPF.getText();
        String nome = tfNome.getText();
        String senha = pfSenha.getText();
        String carteiraMotorista = tfCarteiraMotorista.getText();
        String curso = tfCurso.getText();
        String telefone = tfTelefone.getText();
        String endereco = taEndereco.getText();
        String funcao = (String) cbFuncao.valueProperty().get();

        //dataNascimento = dpDataNascimento.getValue();
        //data = Date.valueOf(dataNascimento);
        Date data = Date.valueOf(LocalDate.now());

        int idade = 22;

        int tel = Integer.valueOf(telefone);
        
        int carteira = Integer.valueOf(carteiraMotorista);

        Result resultado = repositorio.adicionarMotorista(cpf, nome, funcao, senha, data, idade, curso, tel, endereco, carteira);
        
        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult){
            limpar();
        }

        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();
    }

    private void limpar() {
        tfNome.clear();
        tfCPF.clear();
        tfCarteiraMotorista.clear();
        tfCurso.clear();
        tfTelefone.clear();
        taEndereco.clear();
        pfSenha.clear();
    }

}