package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
import ifpr.pgua.eic.projetointegrador.models.results.FailResult;
import ifpr.pgua.eic.projetointegrador.models.results.Result;
import ifpr.pgua.eic.projetointegrador.models.results.SuccessResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class JanelaCadastroCarona implements Initializable {

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
    private Date dRemocao;

    @FXML
    private Date dCancelamento;

    @FXML
    private ComboBox<String> cbCarros;

    private ObservableList<String> listaCarros = FXCollections.observableArrayList();

    private CaronaRepository caronaRepository;

    private CarroRepository carroRepository;

    private MotoristaRepository motoristaRepository;


    public JanelaCadastroCarona(CaronaRepository caronaRepository, CarroRepository carroRepository,
            MotoristaRepository motoristaRepository) {

        this.caronaRepository = caronaRepository;
        this.carroRepository = carroRepository;
        this.motoristaRepository = motoristaRepository;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        List<Carro> carros = carroRepository.listar(motoristaRepository.getUser().getId());

        listaCarros.clear();


        for(Carro c : carros) {
            Carro carro = new Carro(c.getId(), c.getPlaca(), c.getModelo(), c.getQuantidadeLugares(), c.getCor(), c.getId__motorista(), c.isAtivo());
            listaCarros.add(carro.getPlaca());
        }        

        cbCarros.setItems(listaCarros);
    }

    @FXML
    private void cadastrarCarona() throws ParseException{

        boolean ativo = true;
        int id_motorista = motoristaRepository.getUser().getId();
        int id_carro = carroRepository.getByPlaca(cbCarros.getValue()).getId();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        long l = sdf.parse(tfHorarioSaida.getText()).getTime();
        Time horarioSaida = new Time(l);
        int lugaresDisponiveis = Integer.parseInt(tfLugaresDisponiveis.getText());
        String origem = tfOrigem.getText();
        String destino = tfDestino.getText();
        Date dataCadastro = Date.valueOf(LocalDate.now());
        Date dataRemocao = null;
        Date dataCacelamento = null;

        LocalDate data = LocalDate.now();
        data = dpData.getValue();
        Date diaCarona = Date.valueOf(data);


        Carona carona = new Carona(0, id_motorista, id_carro, horarioSaida, lugaresDisponiveis, ativo, origem, destino, dataCadastro, diaCarona, dataRemocao, dataCacelamento);

        Result resultado  = caronaRepository.create(carona);

        String msg = resultado.getMsg();

        if(resultado instanceof SuccessResult) {
            Alert alert = new Alert(AlertType.INFORMATION,msg);
            alert.showAndWait();

        }

        if(resultado instanceof FailResult) {
            Alert alert = new Alert(AlertType.ERROR,msg);
            alert.showAndWait();
        }

    }
}