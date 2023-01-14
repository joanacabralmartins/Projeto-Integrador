package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
                              
      PreparedStatement pstm = con.prepareStatement("INSERT INTO carona(id_motorista, id_carro, horarioSaida, lugaresDisponiveis, ativo, origem, destino, dataCadastro, data, dataRemocao, dataCancelamento) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
      
      pstm.setInt(1, carona.getId_motorista());
      pstm.setInt(2, carona.getId_carro());
      pstm.setTime(3, carona.getHorarioSaida());
      // pstm.setInt(4, carona.getQuantidadeLugares());
      pstm.setInt(4, carona.getLugaresDisponiveis());
      pstm.setBoolean(5, carona.isAtivo());
      // pstm.setInt(7, carona.getId_Origem());
      // pstm.setInt(8, carona.getId_Destino());
      pstm.setString(6, carona.getOrigem());
      pstm.setString(7, carona.getDestino());
      pstm.setDate(8, carona.getDataCadastro());
      pstm.setDate(9, carona.getData());
      pstm.setDate(10, carona.getDataRemocao());
      pstm.setDate(11, carona.getDataCancelamento());

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
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set id_motorista=?, id_carro=?, horarioSaida=?, lugaresDisponiveis=?, ativo=?, origem=?, destino=? dataCadastro=?, data=?, dataRemocao=?, dataCancelamento=? WHERE id=?");
      
      pstm.setInt(1, carona.getId_motorista());
      pstm.setInt(2, carona.getId_carro());
      pstm.setTime(3, carona.getHorarioSaida());
      // pstm.setInt(4, carona.getQuantidadeLugares());
      pstm.setInt(4, carona.getLugaresDisponiveis());
      pstm.setBoolean(6, carona.isAtivo());
      // pstm.setInt(7, carona.getId_Origem());
      // pstm.setInt(8, carona.getId_Destino());
      pstm.setString(5, carona.getOrigem());
      pstm.setString(6, carona.getDestino());
      pstm.setDate(7, carona.getDataCadastro());
      pstm.setDate(8, carona.getData());
      pstm.setDate(9, carona.getDataRemocao());
      pstm.setDate(10, carona.getDataCancelamento());

      pstm.setInt(11, carona.getId());

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
      
      PreparedStatement pstm = con.prepareStatement("UPDATE carona set ativo=0 WHERE id=?");
      
      pstm.setInt(1, carona.getId());

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

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona WHERE id_motorista=? and ativo=1"); 

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
  public Carona getCarona(){
    return carona;
  } 

  @Override
  public void selecionarCarona(Carona caronaa) {

    carona = new Carona(caronaa.getId(), caronaa.getId_motorista(), caronaa.getId_carro(), caronaa.getHorarioSaida(), caronaa.getLugaresDisponiveis(), caronaa.isAtivo(), caronaa.getOrigem(), caronaa.getDestino(), caronaa.getDataCadastro(), caronaa.getData(), caronaa.getDataRemocao(), caronaa.getDataCancelamento());
      
  }

  @Override
  public List<Carona> getByOrigemAndDestino(String origem, String destino) {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where origem=? and destino=? and ativo=1"); 

      pstm.setString(1, origem);
      pstm.setString(2, destino);

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

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where destino=? and ativo=1"); 

      pstm.setString(1, destino);

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

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where origem=? and ativo=1"); 

      pstm.setString(1, origem);

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

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM carona where ativo=1"); 

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
    boolean ativo = rs.getBoolean("ativo");
    // int id_origem = rs.getInt("id_origem");
    // int id_destino = rs.getInt("id_destino");
    String origem = rs.getString("origem");
    String destino = rs.getString("destino");
    Date dataCadastro = rs.getDate("dataCadastro");
    Date data = rs.getDate("data");
    Date dataRemocao = rs.getDate("dataRemocao");
    Date dataCancelamento = rs.getDate("dataCancelamento");

    Carona carona = new Carona(id, id_motorista, id_carro, horarioSaida, lugaresDisponiveis, ativo, origem, destino, dataCadastro, data, dataRemocao, dataCancelamento);

    return carona;

  }

}
