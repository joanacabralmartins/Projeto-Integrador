package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaGerenciarCarros implements Initializable {

    @FXML
    private TableView<Carro> tbCarros;

    @FXML 
    private TableColumn<Carro, String> colPlaca;

    @FXML
    private TableColumn<Carro, String> colModelo;

    @FXML
    private TableColumn<Carro, String> colCor;

    private ObservableList<Carro> listaCarros = FXCollections.observableArrayList();

    private MotoristaRepository repositorioM;
    private CarroRepository repositorioC;

    public JanelaGerenciarCarros(MotoristaRepository repositorioM, CarroRepository repositorioC) {
        this.repositorioM = repositorioM;
        this.repositorioC = repositorioC;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarDados();
    }

    private void carregarDados() {
        colPlaca.setCellValueFactory(new PropertyValueFactory<>("placa"));
        colModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
        colCor.setCellValueFactory(new PropertyValueFactory<>("cor"));

        updateList();
    }

    private void updateList() {
        listaCarros.clear();
        List<Carro> carros = new ArrayList<>(repositorioC.listar(repositorioM.getUser().getCpf()));
        
        for(Carro c : carros) {
            Carro carro = new Carro(c.getPlaca(), c.getModelo(), c.getCor(), c.getCpf_motorista());
            listaCarros.add(carro);
        }
        
        tbCarros.setItems(listaCarros);
    }
    
}
