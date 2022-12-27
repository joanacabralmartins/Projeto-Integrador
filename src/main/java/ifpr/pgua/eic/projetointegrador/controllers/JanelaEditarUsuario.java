package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class JanelaEditarUsuario implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfCPF;

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

    private UsuarioRepository repositorio;

    public JanelaEditarUsuario(UsuarioRepository repositorioUsuario) {
        repositorio = repositorioUsuario;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String cpf = repositorio.getUser().getCpf();
        String nome = repositorio.getUser().getNome();
        String senha = repositorio.getUser().getSenha();
        String curso = repositorio.getUser().getCurso();
        String telefone = repositorio.getUser().getTelefone();
        String endereco = repositorio.getUser().getEndereco();
        
        tfCPF.setText(cpf);
        tfNome.setText(nome);
        tfCurso.setText(curso);
        tfTelefone.setText(telefone);
        taEndereco.setText(endereco);
        pfSenha.setText(senha);
    }
}