package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.models.results.FailResult;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaUsuario implements Initializable {
    
    @FXML
    private TextField tfNome;

    @FXML
    private TextField tfFuncao;

    private MotoristaRepository repositorioM;
    private UsuarioRepository repositorioU;
    private CarroRepository repositorioC;
    private CaronaRepository repositorioCarona;

    private Usuario usuario;
    private Motorista motorista;

    public JanelaUsuario(MotoristaRepository repositorioMotorista, UsuarioRepository repositorioUsuario, CarroRepository repositorioCarro, CaronaRepository repositorioCarona) {
        repositorioM = repositorioMotorista;
        repositorioU = repositorioUsuario;
        repositorioC = repositorioCarro;
        this.repositorioCarona = repositorioCarona;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String nome = null, funcao = null;

        if(repositorioU.getUser() != null) {
            nome = repositorioU.getUser().getNome();
            funcao = repositorioU.getUser().getFuncao_IFPR();

            usuario = repositorioU.getUser();
        } 
        if (repositorioM.getUser() != null) {
            nome = repositorioM.getUser().getNome();
            funcao = repositorioM.getUser().getFuncao_IFPR();

            motorista = repositorioM.getUser();
        }
        
        tfNome.setText(nome);
        tfFuncao.setText(funcao);
    }

    @FXML
    private void carregaTelaEditarUsuario(ActionEvent evento) {
        if (repositorioM.getUser() == null) {
            App.changeScreenRegion("EDITAR USUARIO", BorderPaneRegion.CENTER);
        } else {
            App.changeScreenRegion("EDITAR MOTORISTA", BorderPaneRegion.CENTER);
        }
    }

    @FXML
    private void excluirConta(ActionEvent evento) {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Tem certeza que deseja remover sua conta?");
        Optional<ButtonType> result = alert.showAndWait();

        Result resultado = null;
        String msg = null;

        if (result.get() == ButtonType.OK) {
            if(motorista != null) {
                resultado = repositorioM.inativar(motorista);
                resultado = repositorioU.inativar(usuario);
                repositorioC.inativarByMotorista(motorista.getId());
                repositorioCarona.inativarByMotorista(motorista.getId());
                msg = resultado.getMsg();
            }else if (usuario != null) {
                resultado = repositorioU.inativar(usuario);
                msg = resultado.getMsg();
            }
        } else {
            return;
        }

        if(resultado instanceof SuccessResult){
            Alert alerta = new Alert(AlertType.INFORMATION,msg);
            alerta.showAndWait();
            carregarTelaLogin();
        }

        if(resultado instanceof FailResult) {
            Alert alerta = new Alert(AlertType.ERROR,msg);
            alerta.showAndWait();
        }
    }

    private void carregarTelaLogin() {
        App.pushScreen("LOGIN");
    }
}
