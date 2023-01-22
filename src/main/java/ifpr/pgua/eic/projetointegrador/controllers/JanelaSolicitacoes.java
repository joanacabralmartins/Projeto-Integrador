package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.SolicitacaoRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaSolicitacoes implements Initializable {

    @FXML
    private TableView<SolicitacaoCarona> tbSolicitacoes;

    @FXML 
    private TableColumn<SolicitacaoCarona, String> colDataHora;

    @FXML
    private TableColumn<SolicitacaoCarona, String> colCarona;

    @FXML
    private TableColumn<SolicitacaoCarona, String> colStatus;

    private ObservableList<SolicitacaoCarona> listaSolicitacaoCaronas = FXCollections.observableArrayList();

    private SolicitacaoRepository repositorioS;
    private CaronaRepository repositorioC;
    private UsuarioRepository repositorioU;
    private Usuario usuario;

    public JanelaSolicitacoes(SolicitacaoRepository repositorioS, CaronaRepository repositorioC,
    UsuarioRepository repositorioU) {
        this.repositorioS = repositorioS;
        this.repositorioC = repositorioC;
        this.repositorioU = repositorioU;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        usuario = repositorioU.getUser();
        carregarDados();
    }

    private void carregarDados() {
        colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora_Solicitacao"));
        colCarona.setCellValueFactory(new PropertyValueFactory<>("id_carona"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        updateList();
    }

    private void updateList() {
        listaSolicitacaoCaronas.clear();
        List<SolicitacaoCarona> solicitacoes = new ArrayList<>(repositorioS.getByPassageiro(usuario.getId()));
        
        for(SolicitacaoCarona s : solicitacoes) {
            SolicitacaoCarona solicitacao = new SolicitacaoCarona(s.getId(), s.getId_usuario(), s.getId_motorista(), s.getId_carona(), s.getDataHora_Solicitacao(), LocalDateTime.now(), null, null, s.getStatus());
            listaSolicitacaoCaronas.add(solicitacao);
        }
        
        tbSolicitacoes.setItems(listaSolicitacaoCaronas);
    }

    @FXML
    private void cancelarSolicitacao(ActionEvent actionEvent) {
        SolicitacaoCarona solicitacao = tbSolicitacoes.getSelectionModel().getSelectedItem();

        if(solicitacao == null) {
            Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma solicitação!");
            alert.showAndWait(); 
            return;
        }

        Result resultado = repositorioS.cancelar(solicitacao);

        String msg = resultado.getMsg();
        
        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();

        updateList();
    }
    
}