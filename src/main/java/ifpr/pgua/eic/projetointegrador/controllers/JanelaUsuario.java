package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class JanelaUsuario implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfFuncao;

    private MotoristaRepository repositorioM;
    private UsuarioRepository repositorioU;

    public JanelaUsuario(MotoristaRepository repositorioMotorista, UsuarioRepository repositorioUsuario) {
        repositorioM = repositorioMotorista;
        repositorioU = repositorioUsuario;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String nome = null, funcao = null;

        if(repositorioU.getUser() != null) {
            nome = repositorioU.getUser().getNome();
            funcao = repositorioU.getUser().getFuncao_IFPR();
        } 
        if (repositorioM.getUser() != null) {
            nome = repositorioM.getUser().getNome();
            funcao = repositorioM.getUser().getFuncao_IFPR();
        }
        
        tfNome.setText(nome);
        tfFuncao.setText(funcao);
    }
}
