package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.TimeZone;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.models.results.FailResult;
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

public class JanelaEditarUsuario implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCPF;

    @FXML
    private TextField tfIdade;

    @FXML 
    private ChoiceBox<String> cbFuncao;

    @FXML
    private DatePicker dpDataNascimento;

    @FXML
    private PasswordField pfSenha;

    @FXML
    private TextField tfCurso;

    @FXML
    private TextField tfTelefone;

    @FXML
    private TextArea taEndereco;

    private UsuarioRepository repositorio;
    private String cpf;

    public JanelaEditarUsuario(UsuarioRepository repositorioUsuario) {
        repositorio = repositorioUsuario;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        cpf = repositorio.getUser().getCpf();
        String nome = repositorio.getUser().getNome();
        String senha = repositorio.getUser().getSenha();
        String curso = repositorio.getUser().getCurso();
        String telefone = repositorio.getUser().getTelefone();
        String endereco = repositorio.getUser().getEndereco();
        String funcao = repositorio.getUser().getFuncao_IFPR();
        Date dataNascimento = repositorio.getUser().getData_nascimento();

        String[] opcoes = {"Aluno(a)", "Professor(a)", "Servidor"};
        ObservableList<String> list = FXCollections.observableArrayList(opcoes);
        cbFuncao.setValue(funcao);
        cbFuncao.setItems(list);

        Calendar calend = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));
        calend.setTime(dataNascimento);
        int ano = calend.get(Calendar.YEAR);
        int mes = calend.get(Calendar.MONTH);
        int dia = calend.get(Calendar.DAY_OF_MONTH);

        dpDataNascimento.setValue(LocalDate.of(ano, mes + 1, dia));
        
        tfCPF.setText(cpf);
        tfNome.setText(nome);
        tfCurso.setText(curso);
        tfTelefone.setText(telefone);
        taEndereco.setText(endereco);
        pfSenha.setText(senha);
    }

    @FXML
    private void editarUsuario() {
        String cpfNovo = tfCPF.getText();
        String nome = tfNome.getText();
        String senha = pfSenha.getText();
        String curso = tfCurso.getText();
        String telefone = tfTelefone.getText();
        String endereco = taEndereco.getText();
        String funcao = cbFuncao.valueProperty().get();

        LocalDate data = dpDataNascimento.getValue();
        int idade = Period.between(data, LocalDate.now()).getYears();
        Date dataNascimento = Date.valueOf(data);

        Result resultado = repositorio.editarUsuario(cpf, cpfNovo, nome, funcao, senha, dataNascimento, idade, curso, telefone, endereco);
        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult) {
            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();

            carregarHome();
        }

        if(resultado instanceof FailResult) {
            Alert alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        }

    }

    private void carregarHome() {
        App.pushScreen("PRINCIPAL CARONEIRO");
    }
}