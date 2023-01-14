package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;

import ifpr.pgua.eic.projetointegrador.models.entities.SolicitacaoCarona;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class JDBCSolicitacaoDAO implements SolicitacaoDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCSolicitacaoDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result create(SolicitacaoCarona solicitacao) {
        try {
            Connection con = fabricaConexao.getConnection();
                                    
            PreparedStatement pstm = con.prepareStatement("INSERT INTO solicitacao(id_usuario, id_carona, id_motorista, dataHora_solicitacao, status) VALUES (?,?,?,?,?)");
            
            pstm.setInt(1, solicitacao.getId_usuario());
            pstm.setInt(2, solicitacao.getId_carona());
            pstm.setInt(3, solicitacao.getId_motorista());
            pstm.setString(4, String.valueOf(solicitacao.getDataHora_Solicitacao()));
            pstm.setInt(5, solicitacao.getStatus());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Solicitacao realizada!");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result aceitar(SolicitacaoCarona solicitacao) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE solicitacao set status=3, dataHora_resposta=? WHERE id=?");
            
            pstm.setString(1, String.valueOf(solicitacao.getDataHora_Resposta()));
            pstm.setInt(2, solicitacao.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Solicitação Aceita com Sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result recusar(SolicitacaoCarona solicitacao) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE solicitacao set status=1, dataHora_resposta=? WHERE id=?");
            
            pstm.setString(1, String.valueOf(solicitacao.getDataHora_Resposta()));
            pstm.setInt(2, solicitacao.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Solicitação Recusada com Sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result cancelar(SolicitacaoCarona solicitacao) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE solicitacao set status=2, dataHora_cancelamento=? WHERE id=?");
            
            pstm.setString(1, String.valueOf(solicitacao.getDataCancelamento()));
            pstm.setInt(2, solicitacao.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Solicitação Cancelada com Sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result remover(SolicitacaoCarona solicitacao) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE solicitacao set status=4, dataHora_remocao=? WHERE id=?");
            
            pstm.setString(1, String.valueOf(solicitacao.getDataHora_Remocao()));
            pstm.setInt(2, solicitacao.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Passageiro Removido com Sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public SolicitacaoCarona getById(int id_solicitacao) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM solicitacao WHERE id=?");

            pstm.setInt(1, id_solicitacao);

            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
        
                SolicitacaoCarona solicitacao = buildFrom(rs);
                
                rs.close();
                pstm.close();
                con.close();
        
                return solicitacao;
        
            } else {
              rs.close();
              pstm.close();
              con.close();
              return null;
            }

        }catch(SQLException e ) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<SolicitacaoCarona> getPendenteByMotorista(int id_motorista) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM solicitacao WHERE id_motorista=? and status=0");

            pstm.setInt(1, id_motorista);

            ResultSet rs = pstm.executeQuery();

            ArrayList <SolicitacaoCarona> Solicitacoes = new ArrayList<SolicitacaoCarona>();

            while (rs.next()) {
                Solicitacoes.add(buildFrom(rs));
            } 

            rs.close();
            pstm.close();
            con.close();
    
            return Solicitacoes;

        }catch(SQLException e ) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<SolicitacaoCarona> getPendenteByPassageiro(int id_passageiro) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM solicitacao WHERE id_usuario=? and status=0");

            pstm.setInt(1, id_passageiro);

            ResultSet rs = pstm.executeQuery();

            ArrayList <SolicitacaoCarona> Solicitacoes = new ArrayList<SolicitacaoCarona>();

            while (rs.next()) {
                Solicitacoes.add(buildFrom(rs));
            } 

            rs.close();
            pstm.close();
            con.close();
    
            return Solicitacoes;

        }catch(SQLException e ) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<SolicitacaoCarona> getAceitasByCarona(int id_carona) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM solicitacao WHERE id_carona=? and status=3");

            pstm.setInt(1, id_carona);

            ResultSet rs = pstm.executeQuery();

            ArrayList <SolicitacaoCarona> Solicitacoes = new ArrayList<SolicitacaoCarona>();

            while (rs.next()) {
                Solicitacoes.add(buildFrom(rs));
            } 

            rs.close();
            pstm.close();
            con.close();
    
            return Solicitacoes;

        }catch(SQLException e ) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public List<SolicitacaoCarona> listAll() {

    try {

      Connection con = fabricaConexao.getConnection();

      PreparedStatement pstm = con.prepareStatement("SELECT * FROM solicitacao where status=1"); 

      ResultSet rs = pstm.executeQuery();

      ArrayList <SolicitacaoCarona> solicitacoes = new ArrayList<SolicitacaoCarona>();

      while (rs.next()) {
        solicitacoes.add(buildFrom(rs));
      } 

      rs.close();
      pstm.close();
      con.close();

      return solicitacoes;

    } catch(SQLException e) {
      System.out.println(e.getMessage());
      return Collections.emptyList();
    }   
  }

    private SolicitacaoCarona buildFrom(ResultSet rs) throws SQLException {

        int id = rs.getInt("id");
        int id_carona = rs.getInt("id_carona");
        int id_motorista = rs.getInt("id_motorista");
        int id_usuario = rs.getInt("id_usuario");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        LocalDateTime dataHora_Solicitacao = LocalDateTime.parse(rs.getString("dataHora_Solicitacao"), formatter);
        //LocalDateTime dataHora_Resposta = LocalDateTime.parse(rs.getString("dataHora_Resposta"), formatter);
        //LocalDateTime dataHora_Remocao = LocalDateTime.parse(rs.getString("dataHora_Remocao"), formatter);
        //LocalDateTime dataHora_Cancelamento = LocalDateTime.parse(rs.getString("dataHora_Cancelamento"), formatter);
        
        int status = rs.getInt("status");
  
        SolicitacaoCarona solicitacao = new SolicitacaoCarona(id, id_usuario, id_motorista, id_carona, 
                                        dataHora_Solicitacao, null, null, 
                                        null, status);

        return solicitacao;
  
    }
   
}