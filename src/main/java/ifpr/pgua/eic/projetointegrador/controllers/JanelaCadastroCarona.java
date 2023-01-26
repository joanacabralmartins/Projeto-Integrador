package ifpr.pgua.eic.projetointegrador.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import ifpr.pgua.eic.projetointegrador.App;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.repositories.CaronaRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.CarroRepository;
import ifpr.pgua.eic.projetointegrador.models.repositories.MotoristaRepository;
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
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;

public class JanelaCadastroCarona implements Initializable {
    
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
        cbCarros.setValue(listaCarros.get(0));

        dpData.setValue(LocalDate.now());

        tfLugaresDisponiveis.setTextFormatter(new TextFormatter<>(change -> (change.getControlNewText().matches("([1-9][0-9]*)?")) ? change : null));
    }

    @FXML
    private void cadastrarCarona() throws ParseException{

        String msg;

        if(tfDestino.getText().isBlank() || tfHorarioSaida.getText().isBlank() || tfLugaresDisponiveis.getText().isBlank() || tfOrigem.getText().isBlank()) {
          msg = "Preencha todos os campos!";

          Alert alert = new Alert(AlertType.INFORMATION,msg);
          alert.showAndWait();

          return;
        }
        if(dpData.getValue().isBefore(LocalDate.now())){
          msg = "Data não válida!";

          Alert alert = new Alert(AlertType.INFORMATION,msg);
          alert.showAndWait();

          return;
        }
        if(!validarHorario(tfHorarioSaida.getText())){
          msg = "Horário não válido!";

          Alert alert = new Alert(AlertType.INFORMATION,msg);
          alert.showAndWait();

          return;
        }

        int lugares = Integer.parseInt(tfLugaresDisponiveis.getText());
        
        if(lugares < 1 || lugares > 99){
          msg = "A quantidade de lugares deve ser um número entre 1 e 99!";

          Alert alert = new Alert(AlertType.INFORMATION,msg);
          alert.showAndWait();

          return;
        }

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
        // Date dataRemocao = null;
        // Date dataCacelamento = null;

        LocalDate data = LocalDate.now();
        data = dpData.getValue();
        Date diaCarona = Date.valueOf(data);


        Carona carona = new Carona(0, id_motorista, id_carro, horarioSaida, lugaresDisponiveis, status, origem, destino, dataCadastro, diaCarona, null);

        Result resultado  = caronaRepository.create(carona);

        msg = resultado.getMsg();

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

    public static boolean validarHorario(String horario) {
        try {
            String[] hora = horario.split(":");
            return  Integer.parseInt(hora[0]) < 24 && Integer.parseInt(hora[1]) < 60;
        } catch (Exception e) {
            return false;
        }
    }

    @FXML
    private void voltar() {
        App.changeScreenRegion("CARONA MOTORISTA", BorderPaneRegion.CENTER);
    }

}