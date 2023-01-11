package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaCaronaUsuario implements Initializable {

    @FXML
    private TextField tfOrigem;

    @FXML
    private TextField tfDestino;
  
    @FXML
    private TableView<Carro> carro;

    @FXML 
    private TableColumn<Carro, String> carPlaca;

    @FXML
    private TableColumn<Carro, String> carModelo;

    @FXML
    private TableColumn<Carro, String> carCor;

    @FXML
    private TableView<Usuario> passageiros;

    @FXML 
    private TableColumn<Usuario, String> pasNome;

    @FXML
    private TableColumn<Usuario, String> pasFuncao;

    @FXML
    private TableColumn<Usuario, String> pasCurso;

    @FXML
    private TableColumn<Usuario, Integer> pasIdade;

    @FXML
    private TableColumn<Usuario, String> pasTelefone;

    @FXML
    private TableView<Motorista> motorista;

    @FXML 
    private TableColumn<Motorista, Integer> motoristaId;

    @FXML 
    private TableColumn<Motorista, String> motoristaNome;

    @FXML
    private TableColumn<Motorista, String> motoristaFuncao;

    @FXML
    private TableColumn<Motorista, String> motoristaCurso;

    @FXML
    private TableColumn<Motorista, Integer> motoristaIdade;

    @FXML
    private TableColumn<Motorista, String> motoristaTelefone;

    @FXML
    private TableView<Carona> caronas;

    @FXML 
    private TableColumn<Carona, Integer> caronaId;

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

    @FXML
    private TableView<PontoParada> pontos;

    @FXML 
    private TableColumn<PontoParada, String> pontosDescricao;

    private ObservableList<Carona> listaCaronas = FXCollections.observableArrayList();
    private ObservableList<PontoParada> listaPontos = FXCollections.observableArrayList();
    private ObservableList<Carro> listaCarro = FXCollections.observableArrayList();
    private ObservableList<Motorista> listaMotorista = FXCollections.observableArrayList();
    private ObservableList<Usuario> listaPassageiros = FXCollections.observableArrayList();

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
    }

    private void carregarCaronas() {
      caronaId.setCellValueFactory(new PropertyValueFactory<>("id"));
      caronaOrigem.setCellValueFactory(new PropertyValueFactory<>("origem"));
      caronaDestino.setCellValueFactory(new PropertyValueFactory<>("destino"));
      caronaData.setCellValueFactory(new PropertyValueFactory<>("data"));
      caronaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
      caronaVagas.setCellValueFactory(new PropertyValueFactory<>("vagas"));

      updateListaCarona();
    }

    @FXML
    private void filtrar() {

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
        Carona carona = new Carona(c.getId(), c.getId_motorista(), c.getId_carro(), c.getHorarioSaida(), c.getLugaresDisponiveis(), c.isAtivo(), c.getOrigem(), c.getDestino(), c.getDataCadastro(), c.getData(), c.getDataRemocao(), c.getDataCancelamento());
        listaCaronas.add(carona);
      }
      
      caronas.setItems(listaCaronas);

    }

    @FXML
    private void solicitar() {

      Carona cCarona = caronas.getSelectionModel().getSelectedItem();

      if(cCarona == null){
        Alert alert = new Alert(AlertType.INFORMATION, "Selecione uma carona");
        alert.showAndWait(); 
        return;
      }

      int idM = (Integer) motoristaId.getCellObservableValue(0).getValue();;

      SolicitacaoCarona solicitacao = new SolicitacaoCarona(0, repositorioUsuario.getUser().getId(), idM, cCarona.getId(), LocalDateTime.now(), 0);

      Result resultado = repositorioSolicitacao.create(solicitacao);

      String msg = resultado.getMsg();
      
      Alert alert = new Alert(AlertType.INFORMATION,msg);
      alert.showAndWait();

      updateListaCarona();

    }

    @FXML
    private void carregarDados(){

      pontosDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
      updateListaPontos();

      pasCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
      pasFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
      pasIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
      pasNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
      pasTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));

      updateListaPassageiros();

      motoristaCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
      motoristaFuncao.setCellValueFactory(new PropertyValueFactory<>("funcao"));
      motoristaIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
      motoristaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
      motoristaTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
      motoristaId.setCellValueFactory(new PropertyValueFactory<>("id"));

      updateListaMotorista();

      carCor.setCellValueFactory(new PropertyValueFactory<>("id"));
      carModelo.setCellValueFactory(new PropertyValueFactory<>("placa"));
      carPlaca.setCellValueFactory(new PropertyValueFactory<>("modelo"));

      updateListaCarro();
    }



    private void updateListaCarona() {

      listaCaronas.clear();
      List<Carona> caronasList = new ArrayList<>(repositorioCarona.listAll());
        
      for(Carona c : caronasList) {
        Carona carona = new Carona(c.getId(), c.getId_motorista(), c.getId_carro(), c.getHorarioSaida(), c.getLugaresDisponiveis(), c.isAtivo(), c.getOrigem(), c.getDestino(), c.getDataCadastro(), c.getData(), c.getDataRemocao(), c.getDataCancelamento());
        listaCaronas.add(carona);
      }
      
      caronas.setItems(listaCaronas);

    }

    private void updateListaPontos() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      listaPontos.clear();
      List<PontoParada> pontosList = new ArrayList<>(repositorioPonto.getByCarona(carona.getId()));
        
      for(PontoParada p : pontosList) {
        PontoParada ponto = new PontoParada(p.getId(), carona.getId(), p.getDescricao(), p.getAtivo());
        listaPontos.add(ponto);
      }
      
      pontos.setItems(listaPontos);

    }

    private void updateListaMotorista() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      listaMotorista.clear();
      listaMotorista.add(repositorioMotorista.getById(carona.getId_motorista()));
      
      motorista.setItems(listaMotorista);

    }

    private void updateListaCarro() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      listaCarro.clear();
      listaCarro.add(repositorioCarro.getById(repositorioCarona.getById(carona.getId()).getId_carro()));
      
      carro.setItems(listaCarro);

    }

    private void updateListaPassageiros() {

      Carona carona = caronas.getSelectionModel().getSelectedItem();

      listaPassageiros.clear();
      List<SolicitacaoCarona> solicitacaoList = new ArrayList<>(repositorioSolicitacao.getAceitasByCarona(carona.getId()));
        
      for(SolicitacaoCarona sc : solicitacaoList) {
        Usuario passageiro = repositorioUsuario.getById(sc.getId_usuario());
        listaPassageiros.add(passageiro);
      }
      
      passageiros.setItems(listaPassageiros);

    }

}
