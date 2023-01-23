package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.PontoParada;
import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.PontoRepository;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaCaronaMotorista implements Initializable {
  
    @FXML
    private TextArea taCarro;
    
    @FXML
    private TextArea taPontos;

    @FXML
    private TableView<Usuario> passageiros;

    @FXML
    private TextArea taPassageiros;

    @FXML
    private TableView<Carona> caronas;


    @FXML 
    private TableColumn<Carona, Integer> caronaId;

    @FXML 
    private TableColumn<Carona, Integer> caronaIdCarro;

    @FXML 
    private TableColumn<Carona, Integer> caronaIdMotorista;

    @FXML 
    private TableColumn<Carona, String> caronaOrigem;

    @FXML
    private TableColumn<Carona, String> caronaDestino;

    @FXML
    private TableColumn<Carona, Date> caronaData;

    @FXML
    private TableColumn<Carona, Time> caronaHorario;

    @FXML
    private TableColumn<Carona, Integer> caronaVagas;

    private ObservableList<Carona> listaCaronas = FXCollections.observableArrayList();

    private MotoristaRepository repositorioMotorista;
    private CarroRepository repositorioCarro;
    private CaronaRepository repositorioCarona;
    private PontoRepository repositorioPonto;
    private SolicitacaoRepository repositorioSolicitacao;
    private UsuarioRepository repositorioUsuario;

    public JanelaCaronaMotorista(CarroRepository carroRepository, MotoristaRepository motoristaRepository, CaronaRepository caronaRepository, PontoRepository pontoRepository, SolicitacaoRepository solicitacaoRepository, UsuarioRepository usuarioRepository) {
      this.repositorioMotorista = motoristaRepository;
      this.repositorioCarro = carroRepository;
      this.repositorioCarona = caronaRepository;
      this.repositorioPonto = pontoRepository;
      this.repositorioSolicitacao = solicitacaoRepository;
      this.repositorioUsuario = usuarioRepository;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      carregarCaronas();
    }

    private void carregarCaronas() {
      caronaId.setCellValueFactory(new PropertyValueFactory<>("id"));
      caronaIdCarro.setCellValueFactory(new PropertyValueFactory<>("id_carro"));
      caronaIdMotorista.setCellValueFactory(new PropertyValueFactory<>("id_motorista"));
      caronaOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
      caronaDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
      caronaData.setCellValueFactory(new PropertyValueFactory<>("data"));
      caronaHorario.setCellValueFactory(new PropertyValueFactory<>("horarioSaida"));
      caronaVagas.setCellValueFactory(new PropertyValueFactory<>("lugaresDisponiveis"));

      updateListaCarona();
    }

    @FXML
    private void criarCarona() {

      App.changeScreenRegion("CADASTRO CARONA", BorderPaneRegion.CENTER);

    }

    @FXML
    private void editarCarona() {

      Carona cCarona = caronas.getSelectionModel().getSelectedItem();

      if(cCarona == null){
        Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma carona");
        alert.showAndWait(); 
        return;
      }

      int idC = cCarona.getId();

      repositorioCarona.selecionarCarona(repositorioCarona.getById(idC));

      App.changeScreenRegion("EDITAR CARONA", BorderPaneRegion.CENTER);

    }

    @FXML
    private void inativarCarona() {

      Carona cCarona = caronas.getSelectionModel().getSelectedItem();

      if(cCarona == null){
        Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma carona");
        alert.showAndWait(); 
        return;
      }

      int idC = cCarona.getId();

      Carona carona = repositorioCarona.getById(idC);
      carona.setDataCancelamento(Date.valueOf(LocalDate.now()));

      Result resultado = repositorioCarona.inativar(carona);

      String msg = resultado.getMsg();
      
      Alert alert = new Alert(AlertType.INFORMATION,msg);
      alert.showAndWait();

      updateListaCarona();

    }

    @FXML
    private void carregarDados(){

      Carona cCarona = caronas.getSelectionModel().getSelectedItem();

      if(cCarona == null){
        Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma carona");
        alert.showAndWait(); 
        return;
      }

      updateListaPontos();

      updateListaPassageiros();

      updateListaCarro();

    }



    private void updateListaCarona() {

      listaCaronas.clear();
      List<Carona> caronasList = new ArrayList<>(repositorioCarona.getByMotorista(repositorioMotorista.getUser().getId()));
        
      for(Carona c : caronasList) {
        Carona carona = new Carona(c.getId(), c.getId_motorista(), c.getId_carro(), c.getHorarioSaida(), c.getLugaresDisponiveis(), c.getStatus(), c.getOrigem(), c.getDestino(), c.getDataCadastro(), c.getData(), null);
        listaCaronas.add(carona);
      }
      
      caronas.setItems(listaCaronas);

    }

    private void updateListaPontos() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      taPontos.clear();
      List<PontoParada> pontosList = new ArrayList<>(repositorioPonto.getByCarona(carona.getId()));
        
      for(PontoParada p : pontosList) {
        taPontos.appendText(p.getDescricao() + "\n");
      }
      
    }

    private void updateListaCarro() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();
      Carro carro = repositorioCarro.getById(carona.getId_carro());

      taCarro.clear();
      taCarro.appendText(carro.getModelo() + " " + carro.getCor() + ", Placa: " + carro.getPlaca());
      
    }

    private void updateListaPassageiros() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      taPassageiros.clear();

      List<SolicitacaoCarona> solicitacaoList = new ArrayList<>(repositorioSolicitacao.getAceitasByCarona(carona.getId()));
      List<Usuario> passageirosList = new ArrayList<>();
        
      for(SolicitacaoCarona sc : solicitacaoList) {
        passageirosList.add(repositorioUsuario.getById(sc.getId_usuario()));
      }
      for(Usuario u : passageirosList) {
        taPassageiros.appendText(u.getNome() + ", " + u.getIdade() + " anos, " + u.getFuncao_IFPR());

        if(!u.getCurso().isBlank()){
          taPassageiros.appendText(" de " + u.getCurso());
        }

        taPassageiros.appendText("\n");
      }
      
    }

    @FXML
    private void voltar() {
        App.changeScreenRegion("CARONA MOTORISTA INICIAL", BorderPaneRegion.CENTER);
    }

}
