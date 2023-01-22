package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.SolicitacaoRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaSolicitacoes implements Initializable {

    @FXML
    private TableView<Carona> tbSolicitacoes;

    @FXML 
    private TableColumn<Carona, String> colOrigem;

    @FXML 
    private TableColumn<Carona, String> colDestino;

    @FXML
    private TableColumn<Carona, String> colData;

    @FXML
    private TableColumn<Carona, String> colHr;

    @FXML
    private TableColumn<Carona, String> colVagas;

    private ObservableList<Carona> caronasSolicitadas = FXCollections.observableArrayList();

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
        colOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data"));
        colHr.setCellValueFactory(new PropertyValueFactory<>("horarioSaida"));
        colVagas.setCellValueFactory(new PropertyValueFactory<>("lugaresDisponiveis"));

        updateList();
    }

    private void updateList() {
        caronasSolicitadas.clear();
        List<SolicitacaoCarona> solicitacoes = new ArrayList<>(repositorioS.getByPassageiro(usuario.getId()));
        
        for(SolicitacaoCarona s : solicitacoes) {
            int idCarona = s.getId_carona();
            Carona carona = repositorioC.getById(idCarona);
            caronasSolicitadas.add(carona);
        }
        
        tbSolicitacoes.setItems(caronasSolicitadas);
    }
}