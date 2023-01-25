package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
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

public class JanelaGerenciarCarros implements Initializable {

    @FXML
    private TableView<Carro> tbCarros;

    @FXML 
    private TableColumn<Carro, Integer> colId;

    @FXML 
    private TableColumn<Carro, String> colPlaca;

    @FXML
    private TableColumn<Carro, String> colModelo;

    @FXML
    private TableColumn<Carro, Integer> colLugares;

    @FXML
    private TableColumn<Carro, String> colCor;

    private ObservableList<Carro> listaCarros = FXCollections.observableArrayList();

    private MotoristaRepository repositorioMotorista;
    private CarroRepository repositorioCarro;
    private CaronaRepository repositorioCarona;

    public JanelaGerenciarCarros(MotoristaRepository repositorioMotorista, CarroRepository repositorioCarro, CaronaRepository repositorioCarona) {
        this.repositorioMotorista = repositorioMotorista;
        this.repositorioCarro = repositorioCarro;
        this.repositorioCarona = repositorioCarona;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarDados();
    }

    private void carregarDados() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colLugares.setCellValueFactory(new PropertyValueFactory<>("quantidadeLugares"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));

        updateList();
    }

    @FXML
    private void inativarCarro() {

        Carro carro = tbCarros.getSelectionModel().getSelectedItem();

        if(carro == null){
            Alert alert = new Alert(AlertType.INFORMATION, "Selecione um carro");
            alert.showAndWait(); 
            return;
        }
        
        Result resultado = repositorioCarro.inativar(carro);
        repositorioCarona.inativarByCarro(carro.getId());
        
        String msg = resultado.getMsg();

        Alert alert = new Alert(AlertType.INFORMATION,msg);
        alert.showAndWait();

        updateList();

    }

    @FXML
    private void carregaTelaCadastrarCarro() {       
        App.changeScreenRegion("CADASTRO CARROS", BorderPaneRegion.CENTER);
    }

    @FXML
    private void carregaTelaEditarCarro() {

        Carro carro = tbCarros.getSelectionModel().getSelectedItem();

        if(carro == null){
            Alert alert = new Alert(AlertType.INFORMATION, "Selecione um carro");
            alert.showAndWait(); 
            return;
        }

        List<Carona> caronas = repositorioCarona.getByCarro(carro.getId());

        if(!caronas.isEmpty()){

          Alert alert = new Alert(AlertType.INFORMATION, "Carro não pode ser modificado pois já está sendo utilizado em carona(s)!");
          alert.showAndWait();

          return;
        }

        repositorioCarro.selecionarCarro(carro);
        
        App.changeScreenRegion("EDITAR CARRO", BorderPaneRegion.CENTER);

    }

    private void updateList() {

        listaCarros.clear();
        List<Carro> carros = new ArrayList<>(repositorioCarro.listar(repositorioMotorista.getUser().getId()));
        
        for(Carro c : carros) {
            Carro carro = new Carro(c.getId(), c.getPlaca(), c.getModelo(), c.getQuantidadeLugares(), c.getCor(), c.getId__motorista(), c.isAtivo());
            listaCarros.add(carro);
        }
        
        tbCarros.setItems(listaCarros);

    }

}
