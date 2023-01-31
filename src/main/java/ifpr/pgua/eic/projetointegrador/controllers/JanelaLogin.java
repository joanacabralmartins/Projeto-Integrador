package ifpr.pgua.eic.projetointegrador.controllers;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaLogin extends BaseController {

    @FXML
    private TextField tfUsuario;

    @FXML
    private PasswordField pfSenha;

    private MotoristaRepository repositorioM;
    private UsuarioRepository repositorioU;

    public JanelaLogin(MotoristaRepository repositorioMotorista, UsuarioRepository repositorioUsuario) {
        repositorioM = repositorioMotorista;
        repositorioU = repositorioUsuario;
    }

    @FXML
    private void logar(ActionEvent evento) {

        if (tfUsuario.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Preencha os campos necessários!");
            alert.showAndWait();
            return;
        }
        if (pfSenha.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR, "Preencha os campos necessários!");
            alert.showAndWait();
            return;
        }

        String cpf = tfUsuario.getText();
        String senha = pfSenha.getText();

        if(repositorioU.getByCpf(cpf) == null){
            Alert alert = new Alert(AlertType.ERROR, "Usuario não cadastrado");
            alert.showAndWait();
        }else {
            Result resultadoMotorista = repositorioM.validarLogin(cpf, senha);
            String msgM = resultadoMotorista.getMsg();
            Result resultadoUsuario = repositorioU.validarLogin(cpf, senha);
            String msgU = resultadoUsuario.getMsg();

            if (resultadoMotorista instanceof SuccessResult) {
                Alert alert = new Alert(AlertType.INFORMATION, msgM);
                alert.showAndWait();
                App.pushScreen("PRINCIPAL MOTORISTA");
                return;
            }else if (resultadoUsuario instanceof SuccessResult) {
                Alert alert = new Alert(AlertType.INFORMATION, msgU);
                alert.showAndWait();
                App.pushScreen("PRINCIPAL CARONEIRO");
                return;
            }
            else {
                Alert alert = new Alert(AlertType.ERROR, "Senha incorreta!");
                alert.showAndWait();
            }
            
        }

    }

    @FXML
    private void carregaTelaOpcaoCadastro(ActionEvent evento) {
        App.pushScreen("OPCAO CADASTRO");
    }

}
