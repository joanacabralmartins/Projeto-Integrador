package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaLogin extends BaseController {

    @FXML
    private TextField tfUsuario;

    @FXML
    private TextField tfSenha;

    private MotoristaRepository repositorioM;
    private UsuarioRepository repositorioU;

    public JanelaLogin(MotoristaRepository repositorioMotorista, UsuarioRepository repositorioUsuario) {
        repositorioM = repositorioMotorista;
        repositorioU = repositorioUsuario;
    }

    @FXML
    private void logar(ActionEvent evento) {
        String cpf = tfUsuario.getText();
        String senha = tfSenha.getText();

        Result resultadoMotorista = repositorioM.validarLogin(cpf, senha);
        String msgM = resultadoMotorista.getMsg();
        Result resultadoUsuario = repositorioU.validarLogin(cpf, senha);
        String msgU = resultadoUsuario.getMsg();

        if (resultadoMotorista instanceof SuccessResult) {
            Alert alert = new Alert(AlertType.INFORMATION, msgM);
            alert.showAndWait();
            App.pushScreen("PRINCIPAL");
        }
        if (resultadoUsuario instanceof SuccessResult) {
            Alert alert = new Alert(AlertType.INFORMATION, msgU);
            alert.showAndWait();
            App.pushScreen("PRINCIPAL");
        }
        else {
            Alert alert = new Alert(AlertType.ERROR, "Usuário não cadastrado!");
            alert.showAndWait();
        }
    }

    @FXML
    private void carregaTelaOpcaoCadastro(ActionEvent evento) {
        App.pushScreen("OPCAO CADASTRO");
    }

}
