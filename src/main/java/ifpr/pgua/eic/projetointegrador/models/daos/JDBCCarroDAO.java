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

            PreparedStatement pstm = con.prepareStatement("INSERT INTO carro(placa,modelo,cor,cpf_motorista) VALUES (?,?,?,?)");

            pstm.setString(1, carro.getPlaca());
            pstm.setString(2, carro.getModelo());
            pstm.setString(3, carro.getCor());
            pstm.setString(4, carro.getCpf_motorista());

            pstm.executeUpdate();

            pstm.close();
            con.close();

            return Result.success("Carro cadastrado!");
            

        } catch(SQLException e) {
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public List<Carro> listAll(String cpfMotorista) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM carro where cpf_motorista=?");

            pstm.setString(1, cpfMotorista);

            ResultSet resultSet = pstm.executeQuery();

            ArrayList<Carro> carros = new ArrayList<>();

            while (resultSet.next()) {
                String placa = resultSet.getString("placa");
                String modelo = resultSet.getString("modelo");
                String cor = resultSet.getString("cor");

                Carro carro = new Carro(placa, modelo, cor, null);

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
