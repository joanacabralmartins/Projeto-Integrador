package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.PontoParada;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.PontoRepository;
import ifpr.pgua.eic.projetointegrador.models.results.FailResult;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import ifpr.pgua.eic.projetointegrador.utils.BorderPaneRegion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class JanelaEditarCarona implements Initializable {

    @FXML
    private TextField tfDescricao;
    
    @FXML
    private TextField tfHorarioSaida;

    @FXML
    private TextField tfLugaresDisponiveis;

    @FXML
    private TextField tfOrigem;

    @FXML
    private TextField tfDestino;

    @FXML
    private DatePicker dpData;

    @FXML
    private ComboBox<String> cbCarros;

    @FXML
    private TableView<PontoParada> pontos;

    @FXML 
    private TableColumn<PontoParada, String> pontoDescricao;

    private ObservableList<String> listaCarros = FXCollections.observableArrayList();

    private ObservableList<PontoParada> listaPontos = FXCollections.observableArrayList();

    private CaronaRepository caronaRepository;

    private CarroRepository carroRepository;

    private MotoristaRepository motoristaRepository;

    private PontoRepository pontoRepository;

    private Carona carona;

    public JanelaEditarCarona(CaronaRepository caronaRepository, CarroRepository carroRepository,
            MotoristaRepository motoristaRepository, PontoRepository pontoRepository) {

        this.caronaRepository = caronaRepository;
        this.carroRepository = carroRepository;
        this.motoristaRepository = motoristaRepository;
        this.pontoRepository = pontoRepository;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        this.carona = caronaRepository.getCarona();

        List<Carro> carros = carroRepository.listar(motoristaRepository.getUser().getId());

        listaCarros.clear();

        for(Carro c : carros) {
            Carro carro = new Carro(c.getId(), c.getPlaca(), c.getModelo(), c.getQuantidadeLugares(), c.getCor(), c.getId__motorista(), c.isAtivo());
            listaCarros.add(carro.getPlaca());
        }        

        cbCarros.setItems(listaCarros);
        cbCarros.setValue(carroRepository.getById(carona.getId_carro()).getPlaca());

        tfOrigem.setText(carona.getOrigem());
        tfDestino.setText(carona.getDestino());

        java.sql.Date sqlDate = java.sql.Date.valueOf(carona.getData().toString());
        LocalDate ldData = sqlDate.toLocalDate();

        dpData.setValue(ldData);

        tfLugaresDisponiveis.setText(String.valueOf(carona.getLugaresDisponiveis()));

        tfHorarioSaida.setText(String.valueOf(carona.getHorarioSaida()));

        pontoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        atualizarPontos();

    }

    @FXML
    private void editarCarona() throws ParseException{

        String status = "Em curso";
        int id_motorista = motoristaRepository.getUser().getId();
        int id_carro = carroRepository.getByPlaca(cbCarros.getValue()).getId();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long l = sdf.parse(tfHorarioSaida.getText()).getTime();
        Time horarioSaida = new Time(l);
        int lugaresDisponiveis = Integer.parseInt(tfLugaresDisponiveis.getText());
        String origem = tfOrigem.getText();
        String destino = tfDestino.getText();
        Date dataCadastro = Date.valueOf(LocalDate.now());

        LocalDate data = LocalDate.now();
        data = dpData.getValue();
        Date dataCarona = Date.valueOf(data);
        
        Carona caronaa = new Carona(carona.getId(), id_motorista, id_carro, horarioSaida, lugaresDisponiveis, status, origem, destino, dataCadastro, dataCarona, null);

        Result resultado  = caronaRepository.update(caronaa);

        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult) {
            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();
            App.changeScreenRegion("CARONA MOTORISTA", BorderPaneRegion.CENTER);

        }

        if(resultado instanceof FailResult) {
            Alert alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        }

    }

    @FXML
    private void adicionarPonto() {

        if(tfDescricao.getText().isBlank()) {
            Alert alert = new Alert(AlertType.ERROR,"Insira a descrição do ponto de parada!");
            alert.showAndWait();
        }
        else{

            PontoParada ponto = new PontoParada(0, carona.getId(), tfDescricao.getText(), true);

            Result resultado  = pontoRepository.create(ponto);

            String msg = resultado.getMsg();

            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();

            tfDescricao.clear();
            
        }

        atualizarPontos();

    }

    @FXML
    private void removerPonto() {


      PontoParada pontoo = pontos.getSelectionModel().getSelectedItem();

        if(pontoo == null) {
            Alert alert = new Alert(AlertType.ERROR,"Selecione um ponto para remoção!");
            alert.showAndWait();
        }
        else{

            PontoParada ponto = pontoRepository.getById(pontoo.getId());

            Result resultado  = pontoRepository.inativar(ponto);

            String msg = resultado.getMsg();

            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();
            
        }

        atualizarPontos();

    }

    public void atualizarPontos(){
      
        listaPontos.clear();
        List<PontoParada> pontosList = new ArrayList<>(pontoRepository.getByCarona(carona.getId()));
          
        for(PontoParada p : pontosList) {
            PontoParada ponto = new PontoParada(p.getId(), carona.getId(), p.getDescricao(), p.getAtivo());
            listaPontos.add(ponto);
        }
        
        pontos.setItems(listaPontos);
    }

    @FXML
    private void voltar() {
        App.changeScreenRegion("CARONA MOTORISTA", BorderPaneRegion.CENTER);
    }
    
}