package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.SolicitacaoRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaCaronaUsuarioTeste implements Initializable {

    @FXML
    private TableView<Carona> tbCaronas;

    @FXML 
    private TableColumn<Carona, Integer> colId;

    @FXML 
    private TableColumn<Carona, String> colOrigem;

    @FXML
    private TableColumn<Carona, String> colDestino;

    @FXML
    private TableColumn<Carona, Integer> colHorarioSaida;

    @FXML
    private TableColumn<Carona, String> colLugares;

    @FXML
    private TableColumn<Carona, String> colData;

    private ObservableList<Carona> listaCaronas = FXCollections.observableArrayList();

    private CaronaRepository repositorioC;
    private UsuarioRepository repositorioU;
    private SolicitacaoRepository repositorioS;

    public JanelaCaronaUsuarioTeste(CaronaRepository repositorioC, UsuarioRepository repositorioU, SolicitacaoRepository repositorioS) {
        this.repositorioC = repositorioC;
        this.repositorioU = repositorioU;
        this.repositorioS = repositorioS;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarDados();
    }

    private void carregarDados() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrigem.setCellValueFactory(new PropertyValueFactory<>("Origem"));
        colDestino.setCellValueFactory(new PropertyValueFactory<>("Destino"));
        colHorarioSaida.setCellValueFactory(new PropertyValueFactory<>("horarioSaida"));
        colLugares.setCellValueFactory(new PropertyValueFactory<>("lugaresDisponiveis"));
        colData.setCellValueFactory(new PropertyValueFactory<>("Data"));

        updateList();
    }

    private void updateList() {
        listaCaronas.clear();
        List<Carona> caronas = new ArrayList<>(repositorioC.listAll());
        
        for(Carona c : caronas) {
            Carona carona = new Carona(c.getId(), c.getId_motorista(), c.getId_carro(), c.getHorarioSaida(), c.getLugaresDisponiveis(), true, c.getOrigem(), c.getDestino(), c.getDataCadastro(), c.getData(), null, null);
            listaCaronas.add(carona);
        }
        
        tbCaronas.setItems(listaCaronas);
    }

    @FXML
    private void solicitar() {
        Carona carona = tbCaronas.getSelectionModel().getSelectedItem();

        if(carona == null){
            Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma carona!");
            alert.showAndWait(); 
            return;
        }

      SolicitacaoCarona solicitacao = new SolicitacaoCarona(0, repositorioU.getUser().getId(), carona.getId_motorista(), carona.getId(), LocalDateTime.now(), null, null, null, 0);

      Result resultado = repositorioS.create(solicitacao);

      String msg = resultado.getMsg();
      
      Alert alert = new Alert(AlertType.INFORMATION,msg);
      alert.showAndWait();

      //updateListaCarona();
    }

}
