package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.entities.Carro;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class JDBCMotoristaDAO implements MotoristaDAO {
    private FabricaConexoes fabricaConexao;

    public JDBCMotoristaDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result create(Motorista motorista) {

        try {
            Connection con = fabricaConexao.getConnection();
                                    
            PreparedStatement pstm = con.prepareStatement("INSERT INTO motorista(cpf,nome,funcao_IFPR,senha,data_nascimento,idade,curso,telefone,endereco,carteira_motorista) VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            pstm.setString(1, motorista.getCpf());
            pstm.setString(2, motorista.getNome());
            pstm.setString(3, motorista.getFuncao_IFPR());
            pstm.setString(4, motorista.getSenha());
            pstm.setString(5, String.valueOf(motorista.getData_nascimento()));
            pstm.setInt(6, motorista.getIdade());
            pstm.setString(7, motorista.getCurso());
            pstm.setInt(8, motorista.getTelefone());
            pstm.setString(9, motorista.getEndereco());
            pstm.setInt(10, motorista.getCarteira_motorista());

            pstm.execute();

            pstm.close();
            con.close();
            return Result.success("Motorista cadastrado!");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result adicionarCarro(Carro carro) {
        
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
    public List<Motorista> listAll() {
        try{
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM motorista");

            ResultSet resultSet = pstm.executeQuery();

            ArrayList<Motorista> motoristas = new ArrayList<>();

            while (resultSet.next()) {
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String funcao = resultSet.getString("funcao_IFPR");
                String senha = resultSet.getString("senha");
                Date data = resultSet.getDate("data_nascimento");
                int idade = resultSet.getInt("idade");
                String curso = resultSet.getString("curso");
                int telefone = resultSet.getInt("telefone");
                String endereco = resultSet.getString("endereco");
                int carteira = resultSet.getInt("carteira_motorista");

                Motorista motorista = new Motorista(cpf, nome, funcao, senha, null, idade, curso, telefone, endereco, carteira);

                motoristas.add(motorista);
            }
            
            resultSet.close();
            pstm.close();
            con.close();
            return motoristas;

        }catch(SQLException e ) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }    
}