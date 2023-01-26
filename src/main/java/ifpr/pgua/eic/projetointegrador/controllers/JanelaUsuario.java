package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.SolicitacaoRepository;
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

    private MotoristaRepository repositorioMotorista;
    private UsuarioRepository repositorioUsuario;
    private CarroRepository repositorioCarro;
    private CaronaRepository repositorioCarona;
    private SolicitacaoRepository repositorioSolicitacao;

    private Usuario usuario;
    private Motorista motorista;

    public JanelaUsuario(MotoristaRepository repositorioMotorista, UsuarioRepository repositorioUsuario, CarroRepository repositorioCarro, CaronaRepository repositorioCarona, SolicitacaoRepository repositorioSolicitacao) {
        this.repositorioMotorista = repositorioMotorista;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioCarro = repositorioCarro;
        this.repositorioCarona = repositorioCarona;
        this.repositorioSolicitacao = repositorioSolicitacao;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        String nome = null, funcao = null;

        if(repositorioUsuario.getUser() != null) {
            nome = repositorioUsuario.getUser().getNome();
            funcao = repositorioUsuario.getUser().getFuncao_IFPR();

            usuario = repositorioUsuario.getUser();
        } 
        if (repositorioMotorista.getUser() != null) {
            nome = repositorioMotorista.getUser().getNome();
            funcao = repositorioMotorista.getUser().getFuncao_IFPR();

            motorista = repositorioMotorista.getUser();
        }
        
        tfNome.setText(nome);
        tfFuncao.setText(funcao);
    }

    @FXML
    private void carregaTelaEditarUsuario(ActionEvent evento) {
        if (repositorioMotorista.getUser() == null) {
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
                repositorioMotorista.inativar(motorista);
                repositorioCarro.inativarByMotorista(motorista.getId());
                repositorioCarona.inativarByMotorista(motorista.getId());
            }

            List<SolicitacaoCarona> solicitacoes = repositorioSolicitacao.getByPassageiro(usuario.getId());

            for(SolicitacaoCarona sc : solicitacoes){
                repositorioSolicitacao.cancelar(sc);
            }

            resultado = repositorioUsuario.inativar(usuario);
            msg = resultado.getMsg();

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
