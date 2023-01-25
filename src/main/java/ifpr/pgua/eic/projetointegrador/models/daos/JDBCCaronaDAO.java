package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.entities.Carona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class JDBCCaronaDAO implements CaronaDAO{

  private FabricaConexoes fabricaConexao;
  private Carona carona;

  public JDBCCaronaDAO(FabricaConexoes fabricaConexao) {
    this.fabricaConexao = fabricaConexao;
  }

  @Override
  public Result create(Carona carona) {

    try {
      Connection con = fabricaConexao.getConnection();
                              
      PreparedStatement pstm = con.prepareStatement("INSERT INTO carona(id_motorista, id_carro, horarioSaida, lugaresDisponiveis, status, origem, destino, dataCadastro, data, dataCancelamento) VALUES (?,?,?,?,?,?,?,?,?,?)");
      
      pstm.setInt(1, carona.getId_motorista());
      pstm.setInt(2, carona.getId_carro());
      pstm.setTime(3, carona.getHorarioSaida());
      // pstm.setInt(4, carona.getQuantidadeLugares());
      pstm.setInt(4, carona.getLugaresDisponiveis());

      if(carona.getStatus().matches("Cancelada")){
        pstm.setInt(5, 0);
      }else if(carona.getStatus().matches("Em curso")){
        pstm.setInt(5, 1);
      }else if(carona.getStatus().matches("Concluida")){
        pstm.setInt(5, 2);
      }else {
        pstm.setInt(5, 3);
      }

      // pstm.setInt(7, carona.getId_Origem());
      // pstm.setInt(8, carona.getId_Destino());
      pstm.setString(6, carona.getOrigem());
      pstm.setString(7, carona.getDestino());
      pstm.setDate(8, carona.getDataCadastro());
      pstm.setDate(9, carona.getData());
      // pstm.setDate(10, carona.getDataRemocao());
      pstm.setDate(10, carona.getDataCancelamento());

      pstm.execute();

      pstm.close();
      con.close();
      return Result.success("Carona cadastrada!");

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Result.fail(e.getMessage());
    }
  }

  @Override
  public Result update(Carona carona) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set id_motorista=?, id_carro=?, horarioSaida=?, lugaresDisponiveis=?, status=?, origem=?, destino=?, data=?, dataCancelamento=? WHERE id=?");
      pstm.setInt(1, carona.getId_motorista());
      pstm.setInt(2, carona.getId_carro());
      pstm.setTime(3, carona.getHorarioSaida());
      // pstm.setInt(4, carona.getQuantidadeLugares());
      pstm.setInt(4, carona.getLugaresDisponiveis());
      if(carona.getStatus().matches("Cancelada")){
        pstm.setInt(5, 0);
      }else if(carona.getStatus().matches("Em curso")){
        pstm.setInt(5, 1);
      }else if(carona.getStatus().matches("Concluida")){
        pstm.setInt(5, 2);
      }else {
        pstm.setInt(5, 3);
      }
      // pstm.setInt(7, carona.getId_Origem());
      // pstm.setInt(8, carona.getId_Destino());
      pstm.setString(6, carona.getOrigem());
      pstm.setString(7, carona.getDestino());
      pstm.setDate(8, carona.getData());
      // pstm.setDate(9, carona.getDataRemocao());
      pstm.setDate(9, carona.getDataCancelamento());

      pstm.setInt(10, carona.getId());

      pstm.execute();

      pstm.close();
      con.close();

      return Result.success("Carona atualizada com sucesso!");

    } catch(SQLException e){
      return Result.fail(e.getMessage());
    }
  }

  @Override
  public Result inativar(Carona carona) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set status=0 and dataCancelamento=? WHERE id=?");
      
      pstm.setDate(1, carona.getDataCancelamento());
      pstm.setInt(2, carona.getId());

      pstm.execute();

      pstm.close();
      con.close();

      return Result.success("Carona inativada com sucesso!");

    } catch(SQLException e){
      return Result.fail(e.getMessage());
    }
  }

  @Override
  public Carona getById(int id) {

    try {
      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona WHERE id=?"); 

      pstm.setInt(1, id);

      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {

        Carona carona = buildFrom(rs);
        
        rs.close();
        pstm.close();
        con.close();

        return carona;

      } else {
        rs.close();
        pstm.close();
        con.close();
        return null;
      }

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }   
  }

  @Override
  public List<Carona> getByMotorista(int id_motorista) {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona WHERE id_motorista=? and status=1"); 

      pstm.setInt(1, id_motorista);

      ResultSet rs = pstm.executeQuery();

      ArrayList <Carona> caronas = new ArrayList<Carona>();

      while (rs.next()) {
        caronas.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return caronas;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

  @Override
  public List<Carona> getByCarro(int id_carro) {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona WHERE id_carro=? and status=1"); 

      pstm.setInt(1, id_carro);

      ResultSet rs = pstm.executeQuery();

      ArrayList <Carona> caronas = new ArrayList<Carona>();

      while (rs.next()) {
        caronas.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return caronas;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

  @Override
  public void inativarByMotorista(int id_motorista) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set status=0 and dataCancelamento=? WHERE id_motorista=?");
      
      pstm.setDate(1, Date.valueOf(LocalDate.now()));
      pstm.setInt(2, id_motorista);

      pstm.execute();

      pstm.close();
      con.close();

    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void inativarByCarro(int id_carro) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set status=0 and dataCancelamento=? WHERE id_carro=?");
      
      pstm.setDate(1, Date.valueOf(LocalDate.now()));
      pstm.setInt(2, id_carro);

      pstm.execute();

      pstm.close();
      con.close();

    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }

  @Override 
  public Carona getCarona(){
    return carona;
  } 

  @Override
  public void selecionarCarona(Carona carona) {
    this.carona = carona;
  }

  @Override
  public void adicionarLugarDisponivel(int id_carona) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set lugaresDisponiveis=lugaresDisponiveis+1 WHERE id=?");
      pstm.setInt(1, id_carona);

      pstm.execute();

      pstm.close();
      con.close();

      return;

    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void subtrairLugarDisponivel(int id_carona) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set lugaresDisponiveis=lugaresDisponiveis-1 WHERE id=?");
      pstm.setInt(1, id_carona);

      pstm.execute();

      pstm.close();
      con.close();

      return;

    } catch(SQLException e){
      System.out.println(e.getMessage());
    }
  }

  @Override
  public List<Carona> getByOrigemAndDestino(String origem, String destino) {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where origem LIKE ? and destino LIKE ? and status=1"); 

      pstm.setString(1, origem + "%");
      pstm.setString(2, destino + "%");

      ResultSet rs = pstm.executeQuery();

      ArrayList <Carona> caronas = new ArrayList<Carona>();

      while (rs.next()) {
        caronas.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return caronas;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

  @Override
  public List<Carona> getByDestino(String destino) {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where destino LIKE ? and status=1"); 

      pstm.setString(1, destino + "%");

      ResultSet rs = pstm.executeQuery();

      ArrayList <Carona> caronas = new ArrayList<Carona>();

      while (rs.next()) {
        caronas.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return caronas;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

  @Override
  public List<Carona> getByOrigem(String origem) {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where origem LIKE ? and status=1"); 

      pstm.setString(1, origem + "%");

      ResultSet rs = pstm.executeQuery();

      ArrayList <Carona> caronas = new ArrayList<Carona>();

      while (rs.next()) {
        caronas.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return caronas;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

  @Override
  public List<Carona> listAll() {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where status=1"); 

      ResultSet rs = pstm.executeQuery();

      ArrayList <Carona> caronas = new ArrayList<Carona>();

      while (rs.next()) {
        caronas.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return caronas;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

  private Carona buildFrom(ResultSet rs) throws SQLException {

    int id = rs.getInt("id");
    int id_motorista = rs.getInt("id_motorista");
    int id_carro = rs.getInt("id_carro");
    Time horarioSaida = rs.getTime("horarioSaida");
    // int quantidadeLugares = rs.getInt("quantidadeLugares");
    int lugaresDisponiveis = rs.getInt("lugaresDisponiveis");

    int intStatus = rs.getInt("status");
    String status;

    if(intStatus == 0){

      status = "Cancelada";

    }else if(intStatus == 1){

      status = "Em curso";

    }else if(intStatus == 2){

      status = "Concluida";

    }else{

      status = "Não identificado";

    }

    // int id_origem = rs.getInt("id_origem");
    // int id_destino = rs.getInt("id_destino");
    String origem = rs.getString("origem");
    String destino = rs.getString("destino");
    Date dataCadastro = rs.getDate("dataCadastro");
    Date data = rs.getDate("data");
    // Date dataRemocao = rs.getDate("dataRemocao");
    Date dataCancelamento = rs.getDate("dataCancelamento");

    Carona carona = new Carona(id, id_motorista, id_carro, horarioSaida, lugaresDisponiveis, status, origem, destino, dataCadastro, data, dataCancelamento);

    return carona;

  }

}
