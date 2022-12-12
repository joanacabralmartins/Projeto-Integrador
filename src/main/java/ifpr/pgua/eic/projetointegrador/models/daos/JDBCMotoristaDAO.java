package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}