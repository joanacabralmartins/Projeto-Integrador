package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaCaronaUsuario implements Initializable {

    @FXML
    private Button btVoltar;

    @FXML
    private TextField tfOrigem;

    @FXML
    private TextField tfDestino;
  
    @FXML
    private TextArea taCarro;
    
    @FXML
    private TextArea taPontos;

    @FXML
    private TextArea taPassageiros;

    @FXML
    private TextArea taMotorista;

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
    private UsuarioRepository repositorioUsuario;
    private CarroRepository repositorioCarro;
    private CaronaRepository repositorioCarona;
    private PontoRepository repositorioPonto;
    private SolicitacaoRepository repositorioSolicitacao;

    public JanelaCaronaUsuario(CarroRepository carroRepository, MotoristaRepository motoristaRepository, UsuarioRepository usuarioRepository, CaronaRepository caronaRepository, PontoRepository pontoRepository, SolicitacaoRepository solicitacaoRepository) {
      this.repositorioMotorista = motoristaRepository;
      this.repositorioUsuario = usuarioRepository;
      this.repositorioCarro = carroRepository;
      this.repositorioCarona = caronaRepository;
      this.repositorioPonto = pontoRepository;
      this.repositorioSolicitacao = solicitacaoRepository;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
      carregarCaronas();
      if(repositorioMotorista.getUser() instanceof Motorista){
        btVoltar.setDisable(false);
        btVoltar.setVisible(true);
      }else{
        btVoltar.setDisable(true);
        btVoltar.setVisible(false);
      }
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
    private void filtrar() {

      listaCaronas.clear();

      List<Carona> caronaList = new ArrayList<>();

      if(tfDestino.getText().isEmpty() && tfOrigem.getText().isBlank()){
        Alert alert = new Alert(AlertType.INFORMATION, "Preencha um filtro");
        alert.showAndWait(); 
        return;
      }
      else{
        if(!tfDestino.getText().isEmpty() && !tfOrigem.getText().isBlank()){
          caronaList = repositorioCarona.getByOrigemAndDestino(tfOrigem.getText(), tfDestino.getText());
        }
        else{
          if(!tfDestino.getText().isEmpty() && tfOrigem.getText().isBlank()){
            caronaList = repositorioCarona.getByDestino(tfDestino.getText());
          }else if(tfDestino.getText().isEmpty() && !tfOrigem.getText().isBlank()){
            caronaList = repositorioCarona.getByOrigem(tfOrigem.getText());
          }
        }
      }

      for(Carona c : caronaList) {
        Carona carona = new Carona(c.getId(), c.getId_motorista(), c.getId_carro(), c.getHorarioSaida(), c.getLugaresDisponiveis(), c.getStatus(), c.getOrigem(), c.getDestino(), c.getDataCadastro(), c.getData(), c.getDataCancelamento());
        listaCaronas.add(carona);
      }
      
      caronas.setItems(listaCaronas);

    }

    @FXML
    private void solicitar() {

      Carona cCarona = caronas.getSelectionModel().getSelectedItem();

      if(cCarona == null){
        Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma carona!");
        alert.showAndWait(); 
        return;
      }
      
      List<SolicitacaoCarona> solicitacoes = repositorioSolicitacao.getByPassageiro(repositorioUsuario.getUser().getId());

      for(SolicitacaoCarona sc : solicitacoes){
        if(sc.getId_carona() == cCarona.getId()){
          Alert alert = new Alert(AlertType.INFORMATION, "Solicitação já realizada!");
          alert.showAndWait(); 
          return;
        }
      }

      int idM = cCarona.getId_motorista();

      SolicitacaoCarona solicitacao = new SolicitacaoCarona(0, repositorioUsuario.getUser().getId(), idM, cCarona.getId(), LocalDateTime.now(), "Pendente");

      Result resultado = repositorioSolicitacao.create(solicitacao);

      String msg = resultado.getMsg();
      
      Alert alert = new Alert(AlertType.INFORMATION,msg);
      alert.showAndWait();

      updateListaCarona();

    }

    @FXML
    private void carregarDados(){

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      if(carona == null){
        Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma carona");
        alert.showAndWait(); 
        return;
      }

      updateListaPontos();

      updateListaPassageiros();

      updateListaMotorista();

      updateListaCarro();

    }



    private void updateListaCarona() {

      listaCaronas.clear();
      List<Carona> caronasList = repositorioCarona.listAll();
        
      for(Carona c : caronasList) {
        if(c.getId_motorista() != repositorioUsuario.getUser().getId()){
          Carona carona = new Carona(c.getId(), c.getId_motorista(), c.getId_carro(), c.getHorarioSaida(), c.getLugaresDisponiveis(), c.getStatus(), c.getOrigem(), c.getDestino(), c.getDataCadastro(), c.getData(), c.getDataCancelamento());
          listaCaronas.add(carona);
        }
      }
      
      caronas.setItems(listaCaronas);

    }

    private void updateListaPontos() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      taPontos.clear();
      List<PontoParada> pontosList = repositorioPonto.getByCarona(carona.getId());
        
      for(PontoParada p : pontosList) {
        taPontos.appendText(p.getDescricao() + "\n");
      }
      
    }

    private void updateListaMotorista() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      taMotorista.clear();

      Motorista motorista = repositorioMotorista.getById(carona.getId_motorista());
        
      taMotorista.appendText(motorista.getNome() + ", " + motorista.getIdade() + " anos, " + motorista.getFuncao_IFPR());

      if(!motorista.getCurso().isBlank()){
        taMotorista.appendText(" de " + motorista.getCurso());
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
