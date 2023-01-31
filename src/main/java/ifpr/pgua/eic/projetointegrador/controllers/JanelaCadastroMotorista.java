package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
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
    private ChoiceBox<String> cbFuncao;

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

    private MotoristaRepository repositorioM;

    private UsuarioRepository repositorioU;

    public JanelaCadastroMotorista(MotoristaRepository repositorioM, UsuarioRepository repositorioU) {
        this.repositorioM = repositorioM;
        this.repositorioU = repositorioU;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String[] opcoes = {"Aluno(a)", "Professor(a)", "Servidor"};
        ObservableList<String> list = FXCollections.observableArrayList(opcoes);
        cbFuncao.setItems(list);

        dpDataNascimento.setValue(LocalDate.of(2004, 01, 01));
    }

    @FXML
    private void cadastrarMotorista() {
        if(tfCPF.getText().isEmpty() || tfNome.getText().isEmpty() || pfSenha.getText().isEmpty()
        || tfCarteiraMotorista.getText().isEmpty() || cbFuncao.valueProperty().get() == null) {
            Alert alert = new Alert(AlertType.ERROR, "Preencha todos os campos necessários!");
            alert.showAndWait();

            return;
        }

        boolean ativo = true;
        String cpf = tfCPF.getText();
        String nome = tfNome.getText();
        String senha = pfSenha.getText();
        String carteiraMotorista = tfCarteiraMotorista.getText();
        String curso = tfCurso.getText();
        String telefone = tfTelefone.getText();
        String endereco = taEndereco.getText();
        String funcao = (String) cbFuncao.valueProperty().get();

        LocalDate data = LocalDate.now();
        data = dpDataNascimento.getValue();
        int idade = Period.between(data, LocalDate.now()).getYears();
        Date dataNascimento = Date.valueOf(data);

        Result resultado = repositorioU.adicionarUsuario(ativo, cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult) {

            resultado = repositorioM.adicionarMotorista(repositorioU.getByCpf(cpf).getId(), ativo, cpf, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco, carteiraMotorista);

            if(resultado instanceof SuccessResult) {

                Alert alert = new Alert(AlertType.INFORMATION,msg);
                alert.showAndWait();

                App.popScreen();

            }

        }else{
            msg = resultado.getMsg();

            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();
        }
        
    }

}