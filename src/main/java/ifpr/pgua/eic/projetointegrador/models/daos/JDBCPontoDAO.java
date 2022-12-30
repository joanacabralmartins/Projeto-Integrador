package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.entities.PontoParada;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class JDBCPontoDAO implements PontoDAO{

  private FabricaConexoes fabricaConexao;

  public JDBCPontoDAO(FabricaConexoes fabricaConexao) {
    this.fabricaConexao = fabricaConexao;
  }

  @Override
  public Result create(PontoParada ponto) {

    try {
      Connection con = fabricaConexao.getConnection();
                              
      PreparedStatement pstm = con.prepareStatement("INSERT INTO ponto(id_carona, descricao, status) VALUES (?,?,?)");
      
      pstm.setInt(1, ponto.getId_carona());
      pstm.setString(2, ponto.getDescricao());
      pstm.setInt(3, ponto.getStatus());

      pstm.execute();

      pstm.close();
      con.close();
      return Result.success("Ponto cadastrado!");

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Result.fail(e.getMessage());
    }
  }

  @Override
  public Result update(PontoParada ponto) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE ponto set descricao=?, status=? WHERE id=?");
      
      pstm.setString(1, ponto.getDescricao());
      pstm.setInt(2, ponto.getStatus());
      pstm.setInt(3, ponto.getId());

      pstm.execute();

      pstm.close();
      con.close();

      return Result.success("Ponto atualizado com sucesso!");

    } catch(SQLException e){
      return Result.fail(e.getMessage());
    }
  }

  @Override
  public Result delete(PontoParada ponto) {
    try {
      Connection con = fabricaConexao.getConnection(); 
      
      PreparedStatement pstm = con.prepareStatement("UPDATE ponto set status=? WHERE id=?");
      
      pstm.setInt(1, ponto.getStatus());
      pstm.setInt(2, ponto.getId());

      pstm.execute();

      pstm.close();
      con.close();

      return Result.success("Ponto deletado com sucesso!");

    } catch(SQLException e){
      return Result.fail(e.getMessage());
    }
  }

  @Override
  public PontoParada getById(int id_ponto) {

    try {
      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM ponto WHERE id=?"); 

      pstm.setInt(1, id_ponto);

      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {

        int id = rs.getInt("id");
        int id_carona = rs.getInt("id_carona");
        String descricao = rs.getString("descricao");
        int status = rs.getInt("status");

        PontoParada ponto = new PontoParada(id, id_carona, descricao, status);
        
        rs.close();
        pstm.close();
        con.close();

        return ponto;

      } else {
        return null;
      }

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return null;
    }   
  }

  @Override
  public List<PontoParada> getByCarona(int id_carona) {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM ponto WHERE id_carona=?"); 

      pstm.setInt(1, id_carona);

      ResultSet rs = pstm.executeQuery();

      ArrayList <PontoParada> pontos = new ArrayList<PontoParada>();

      while (rs.next()) {
        pontos.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return pontos;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

  private PontoParada buildFrom(ResultSet rs) throws SQLException {

    int id = rs.getInt("id");
    int id_carona = rs.getInt("id_carona");
    String descricao = rs.getString("descricao");
    int status = rs.getInt("status");

    PontoParada ponto = new PontoParada(id, id_carona, descricao, status);

    return ponto;

  }

}
