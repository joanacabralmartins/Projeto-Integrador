package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ifpr.pgua.eic.projetointegrador.models.FabricaConexoes;
import ifpr.pgua.eic.projetointegrador.models.entities.Motorista;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class JDBCMotoristaDAO implements MotoristaDAO {
    private FabricaConexoes fabricaConexao;
    private Motorista motorista;

    public JDBCMotoristaDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result create(Motorista motorista) {
        try {
            Connection con = fabricaConexao.getConnection();
                                    
            PreparedStatement pstm = con.prepareStatement("INSERT INTO motorista(id,ativo,cpf,nome,funcao_IFPR,senha,data_nascimento,idade,curso,telefone,endereco,carteira_motorista) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
            
            pstm.setInt(1, motorista.getId());
            pstm.setBoolean(2, motorista.isAtivo());
            pstm.setString(3, motorista.getCpf());
            pstm.setString(4, motorista.getNome());
            pstm.setString(5, motorista.getFuncao_IFPR());
            pstm.setString(6, motorista.getSenha());
            pstm.setString(7, String.valueOf(motorista.getData_nascimento()));
            pstm.setInt(8, motorista.getIdade());
            pstm.setString(9, motorista.getCurso());
            pstm.setString(10, motorista.getTelefone());
            pstm.setString(11, motorista.getEndereco());
            pstm.setString(12, motorista.getCarteira_motorista());

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
    public List<Motorista> listAll() {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM motorista where ativo=1");

            ResultSet resultSet = pstm.executeQuery();

            ArrayList<Motorista> motoristas = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                boolean ativo = resultSet.getBoolean("ativo");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String funcao = resultSet.getString("funcao_IFPR");
                String senha = resultSet.getString("senha");
                String date = resultSet.getString("data_nascimento");
                int idade = resultSet.getInt("idade");
                String curso = resultSet.getString("curso");
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");
                String carteira = resultSet.getString("carteira_motorista");

                LocalDate dataNascimento = LocalDate.parse(date);
                Date data = Date.valueOf(dataNascimento);

                Motorista motorista = new Motorista(id, ativo, cpf, nome, funcao, senha, data, idade, curso, telefone, endereco, carteira);

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

    @Override
    public Motorista getByCpf(String cpf) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM motorista WHERE cpf=? and ativo=1"); 

            pstm.setString(1, cpf);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                int id = rs.getInt("id");
                boolean ativo = rs.getBoolean("ativo");
                String nome = rs.getString("nome");
                String funcao = rs.getString("funcao_IFPR");
                String curso = rs.getString("curso");
                String tel = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                String senha = rs.getString("senha");
                String carteira = rs.getString("carteira_motorista");
                int idade = rs.getInt("idade");
                String dataNascimento = rs.getString("data_nascimento");

                Date dtDataNascimento = Date.valueOf(dataNascimento);

                Motorista motorista = new Motorista(id, ativo, cpf, nome, funcao, senha, dtDataNascimento, idade, curso, tel, endereco, carteira);
                
                pstm.close();
                con.close();
                return motorista;
            } else {
                return null;
            }
        } catch(SQLException e) {
            return null;
        }   
    }

    @Override
    public Motorista getById(int id) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM motorista WHERE id=? and ativo=1"); 

            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                boolean ativo = rs.getBoolean("ativo");
                String nome = rs.getString("nome");
                String funcao = rs.getString("funcao_IFPR");
                String cpf = rs.getString("cpf");
                String curso = rs.getString("curso");
                String tel = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                String senha = rs.getString("senha");
                int idade = rs.getInt("idade");
                String dataNascimento = rs.getString("data_nascimento");
                String carteira = rs.getString("carteira_motorista");

                Date dtDataNascimento = Date.valueOf(dataNascimento);

                Motorista motorist = new Motorista(id, ativo, cpf, nome, funcao, senha, dtDataNascimento, idade, curso, tel, endereco, carteira);
                
                pstm.close();
                con.close();
                return motorist;
            } else {
                return null;
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }   
    }

    @Override
    public Result validarLogin(String cpf, String senha) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM motorista WHERE cpf=? and senha=? and ativo=1"); 

            pstm.setString(1, cpf);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                int id = rs.getInt("id");
                boolean ativo = rs.getBoolean("ativo");
                String nome = rs.getString("nome");
                String funcao = rs.getString("funcao_IFPR");
                String carteira = rs.getString("carteira_motorista");
                String curso = rs.getString("curso");
                String tel = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                int idade = rs.getInt("idade");
                String dataNascimento = rs.getString("data_nascimento");

                Date dtDataNascimento = Date.valueOf(dataNascimento);

                Motorista motorista = new Motorista(id, ativo, cpf, nome, funcao, senha, dtDataNascimento, idade, curso, tel, endereco, carteira);

                this.motorista = motorista;

                pstm.close();
                con.close();
                
                return Result.success("Seja bem vindo(a)!");
            } else {
                motorista = null;
                return Result.fail("Usuário sem cadastro");
            }

        } catch(SQLException e) {
            return Result.fail(e.getMessage());
        }   
    }

    @Override
    public Result update(String cpf, String cpfNovo, String carteira, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE motorista set cpf=?, carteira_motorista=?, nome=?, funcao_IFPR=?, senha=?, data_nascimento=?, idade=?, curso=?, telefone=?, endereco=? WHERE cpf=?");
            
            pstm.setString(1, cpfNovo);
            pstm.setString(2, carteira);
            pstm.setString(3, nome);
            pstm.setString(4, funcao);
            pstm.setString(5, senha);
            pstm.setString(6, String.valueOf(dataNascimento));
            pstm.setInt(7, idade);
            pstm.setString(8, curso);
            pstm.setString(9, telefone);
            pstm.setString(10, endereco);
            pstm.setString(11, cpf);

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Usuário atualizado com sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result inativar(Motorista motorista) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE motorista set ativo=0 WHERE id=?");
            
            pstm.setInt(1, motorista.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Usuario inativado com sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Motorista getUser() {
        return motorista;
    }    

}