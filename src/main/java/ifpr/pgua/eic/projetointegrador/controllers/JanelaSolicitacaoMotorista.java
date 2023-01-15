package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.SolicitacaoRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.UsuarioRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class JanelaSolicitacaoMotorista implements Initializable {

    @FXML
    private TableView<SolicitacaoCarona> tbSolicitacoes;

    @FXML
    private TextArea taDetalhes;

    @FXML 
    private TableColumn<SolicitacaoCarona, String> colPassageiro;

    @FXML 
    private TableColumn<SolicitacaoCarona, String> colDataHora;

    @FXML
    private TableColumn<SolicitacaoCarona, String> colCarona;


    private ObservableList<SolicitacaoCarona> listaSolicitacaoCaronas = FXCollections.observableArrayList();

    private SolicitacaoRepository repositorioS;
    private CaronaRepository repositorioC;
    private UsuarioRepository repositorioU;
    private MotoristaRepository repositorioM;
    private Motorista motorista;

    public JanelaSolicitacaoMotorista(SolicitacaoRepository repositorioS, CaronaRepository 
    repositorioC, UsuarioRepository repositorioU, MotoristaRepository repositorioM) {
        this.repositorioS = repositorioS;
        this.repositorioC = repositorioC;
        this.repositorioU = repositorioU;
        this.repositorioM = repositorioM;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        motorista = repositorioM.getUser();
        carregarDados();
    }

    private void carregarDados() {
        colPassageiro.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        colDataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora_Solicitacao"));
        colCarona.setCellValueFactory(new PropertyValueFactory<>("id_carona"));

        updateList();
    }

    private void updateList() {
        listaSolicitacaoCaronas.clear();
        List<SolicitacaoCarona> solicitacoes = new ArrayList<>(repositorioS.getPendenteByMotorista(motorista.getId()));
        
        for(SolicitacaoCarona s : solicitacoes) {
            SolicitacaoCarona solicitacao = new SolicitacaoCarona(0, s.getId_usuario(), s.getId_motorista(), s.getId_carona(), s.getDataHora_Solicitacao(), LocalDateTime.now(), null, null, 0);
            listaSolicitacaoCaronas.add(solicitacao);
        }
        
        tbSolicitacoes.setItems(listaSolicitacaoCaronas);
    }

    @FXML
    private void exibirDetalhes(MouseEvent evento) {
    SolicitacaoCarona solicitacaoCarona = tbSolicitacoes.getSelectionModel().getSelectedItem();
    String origem = repositorioC.getById(solicitacaoCarona.getId_carona()).getOrigem();
    String destino = repositorioC.getById(solicitacaoCarona.getId_carona()).getDestino();
    Usuario passageiro = repositorioU.getById(solicitacaoCarona.getId_usuario());


    if (solicitacaoCarona != null) {
      taDetalhes.clear();
      taDetalhes.appendText("Origem: " + origem + "\n");
      taDetalhes.appendText("Destino: " + destino + "\n");
      taDetalhes.appendText("Passageiro [Nome: " + passageiro.getNome() 
      + ",\n Idade: " + passageiro.getIdade() + ",\n FunçãoIFPR: " + passageiro.getFuncao_IFPR() + "]");
    }
  }
}
