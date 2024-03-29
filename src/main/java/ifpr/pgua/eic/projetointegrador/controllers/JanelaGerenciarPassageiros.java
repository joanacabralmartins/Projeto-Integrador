package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.SolicitacaoRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaGerenciarPassageiros implements Initializable {

    @FXML
    private TableView<Usuario> tbPassageiros;

    @FXML 
    private TableColumn<Usuario, String> colNome;

    @FXML 
    private TableColumn<Usuario, String> colFuncao;

    @FXML
    private TableColumn<Usuario, String> colCurso;

    @FXML
    private TableColumn<Usuario, String> colTelefone;

    @FXML
    private TableColumn<Usuario, Integer> colIdade;

    private ObservableList<Usuario> listaPassageiros = FXCollections.observableArrayList();

    private UsuarioRepository repositorioU;
    private CaronaRepository repositorioC;
    private SolicitacaoRepository repositorioS;

    private Carona carona;

    public JanelaGerenciarPassageiros(UsuarioRepository repositorioU, CaronaRepository repositorioC,
    SolicitacaoRepository repositorioS) {
        this.repositorioU = repositorioU;
        this.repositorioC = repositorioC;
        this.repositorioS = repositorioS;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carona = repositorioC.getCarona();

        carregarDados();
    }

    private void carregarDados() {
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao_IFPR"));
        colCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));

        updateList();
    }

    private void updateList() {

        listaPassageiros.clear();
        List<SolicitacaoCarona> solicitacoes = new ArrayList<>(repositorioS.getAceitasByCarona(carona.getId()));
        
        for(SolicitacaoCarona s : solicitacoes) {
            int id = s.getId_usuario();
            Usuario usuario = repositorioU.getById(id);
            listaPassageiros.add(usuario);
        }
        
        tbPassageiros.setItems(listaPassageiros);

    }

    @FXML
    private void removerPassageiro() {

        Usuario usuario = tbPassageiros.getSelectionModel().getSelectedItem();
        List<SolicitacaoCarona> solicitacoes = new ArrayList<>(repositorioS.getAceitasByCarona(carona.getId()));
        SolicitacaoCarona solicitacao = new SolicitacaoCarona(0, 0, 0, 0, null, null, null, null, null);
        
        if(usuario == null){
            Alert alert = new Alert(AlertType.INFORMATION, "Selecione um passageiro");
            alert.showAndWait(); 
            return;
        }

        for(SolicitacaoCarona s : solicitacoes) {
            if(usuario.getId() == s.getId_usuario()) {
                solicitacao = s;
            }
        }
        
        Result resultado = repositorioS.remover(solicitacao);
        
        String msg = resultado.getMsg();

        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();

        updateList();

    }


    @FXML
    private void voltar() {
      App.changeScreenRegion("EDITAR CARONA", BorderPaneRegion.CENTER);
    }
}
