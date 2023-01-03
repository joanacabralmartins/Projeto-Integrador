package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class JDBCCarroDAO implements CarroDAO {

    private FabricaConexoes fabricaConexao;

    public JDBCCarroDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result create(Carro carro) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("INSERT INTO carro(placa,modelo,cor,id_motorista,ativo) VALUES (?,?,?,?,?)");

            pstm.setString(1, carro.getPlaca());
            pstm.setString(2, carro.getModelo());
            pstm.setString(3, carro.getCor());
            pstm.setInt(4, carro.getId__motorista());
            pstm.setBoolean(5, carro.isAtivo());

            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Carro cadastrado!");
            

        } catch(SQLException e) {
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result update(Carro carro) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE carro set placa=?, modelo=?, cor=?, id_motorista=?, ativo=? WHERE id=?");

            pstm.setString(1, carro.getPlaca());
            pstm.setString(2, carro.getModelo());
            pstm.setString(3, carro.getCor());
            pstm.setInt(4, carro.getId__motorista());
            pstm.setBoolean(5, carro.isAtivo());

            pstm.setInt(5, carro.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Carro atualizado com sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result inativar(Carro carro) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE carro set ativo=0 WHERE id=?");
            
            pstm.setInt(1, carro.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Carro inativado com sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public List<Carro> listAll(int id_motorista) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM carro where id_motorista=? and ativo=1");

            pstm.setInt(1, id_motorista);

            ResultSet resultSet = pstm.executeQuery();

            ArrayList<Carro> carros = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String placa = resultSet.getString("placa");
                String modelo = resultSet.getString("modelo");
                String cor = resultSet.getString("cor");
                int idmotorista = resultSet.getInt("id_motorista");
                Boolean ativo = resultSet.getBoolean("ativo");

                Carro carro = new Carro(id, placa, modelo, cor, idmotorista, ativo);

                carros.add(carro);
            }
            
            resultSet.close();
            pstm.close();
            con.close();
            return carros;

        }catch(SQLException e ) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }
    
}
