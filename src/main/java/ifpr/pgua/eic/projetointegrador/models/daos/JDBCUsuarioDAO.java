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
import ifpr.pgua.eic.projetointegrador.models.entities.Usuario;
import ifpr.pgua.eic.projetointegrador.models.results.Result;

public class JDBCUsuarioDAO implements UsuarioDAO{

    private FabricaConexoes fabricaConexao;
    private Usuario usuario;

    public JDBCUsuarioDAO(FabricaConexoes fabricaConexao) {
        this.fabricaConexao = fabricaConexao;
    }

    @Override
    public Result create(Usuario caroneiro) {

        try {
            Connection con = fabricaConexao.getConnection();
                                    
            PreparedStatement pstm = con.prepareStatement("INSERT INTO usuario(ativo,cpf,nome,funcao_IFPR,senha,data_nascimento,idade,curso,telefone,endereco) VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            pstm.setBoolean(1, caroneiro.isAtivo());
            pstm.setString(2, caroneiro.getCpf());
            pstm.setString(3, caroneiro.getNome());
            pstm.setString(4, caroneiro.getFuncao_IFPR());
            pstm.setString(5, caroneiro.getSenha());
            pstm.setString(6, String.valueOf(caroneiro.getData_nascimento()));
            pstm.setInt(7, caroneiro.getIdade());
            pstm.setString(8, caroneiro.getCurso());
            pstm.setString(9, caroneiro.getTelefone());
            pstm.setString(10, caroneiro.getEndereco());

            pstm.execute();

            pstm.close();
            con.close();
            return Result.success("Usuário cadastrado!");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public List<Usuario> listAll() {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM usuario");

            ResultSet resultSet = pstm.executeQuery();

            ArrayList<Usuario> usuarios = new ArrayList<>();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                Boolean ativo = resultSet.getBoolean("ativo");
                String cpf = resultSet.getString("cpf");
                String nome = resultSet.getString("nome");
                String funcao = resultSet.getString("funcao_IFPR");
                String senha = resultSet.getString("senha");
                String date = resultSet.getString("data_nascimento");
                int idade = resultSet.getInt("idade");
                String curso = resultSet.getString("curso");
                String telefone = resultSet.getString("telefone");
                String endereco = resultSet.getString("endereco");

                LocalDate dataNascimento = LocalDate.parse(date);
                Date data = Date.valueOf(dataNascimento);

                Usuario usuario = new Usuario(id, ativo, cpf, nome, funcao, senha, data, idade, curso, telefone, endereco);

                usuarios.add(usuario);
            }
            
            resultSet.close();
            pstm.close();
            con.close();
            return usuarios;

        }catch(SQLException e ) {
            System.out.println(e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public Result validarLogin(String cpf, String senha) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM usuario WHERE cpf=? and senha=? and ativo=1"); 

            pstm.setString(1, cpf);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {

                int id = rs.getInt("id");
                boolean ativo = rs.getBoolean("ativo");
                String nome = rs.getString("nome");
                String funcao = rs.getString("funcao_IFPR");
                String curso = rs.getString("curso");
                String tel = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                int idade = rs.getInt("idade");
                String dataNascimento = rs.getString("data_nascimento");

                Date dtDataNascimento = Date.valueOf(dataNascimento);

                usuario = new Usuario(id, ativo, cpf, nome, funcao, senha, dtDataNascimento, idade, curso, tel, endereco);
                
                pstm.close();
                con.close();
                return Result.success("Seja bem vindo(a)!");
            } else {
                return Result.fail("Usuário sem cadastro");
            }
        } catch(SQLException e) {
            return Result.fail(e.getMessage());
        }   
    }

    @Override
    public Usuario getByCpf(String cpf) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM usuario WHERE cpf=? and ativo=1"); 

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
                int idade = rs.getInt("idade");
                String dataNascimento = rs.getString("data_nascimento");

                Date dtDataNascimento = Date.valueOf(dataNascimento);

                usuario = new Usuario(id, ativo, cpf, nome, funcao, senha, dtDataNascimento, idade, curso, tel, endereco);
                
                pstm.close();
                con.close();
                return usuario;
            } else {
                return null;
            }
        } catch(SQLException e) {
            return null;
        }   
    }

    @Override
    public Result update(String cpf, String cpfNovo, String nome, String funcao, String senha, Date dataNascimento, int idade, String curso, String telefone, String endereco) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE usuario set cpf=?, nome=?, funcao_IFPR=?, senha=?, data_nascimento=?, idade=?, curso=?, telefone=?, endereco=? WHERE cpf=?");
            
            pstm.setString(1, cpfNovo);
            pstm.setString(2, nome);
            pstm.setString(3, funcao);
            pstm.setString(4, senha);
            pstm.setString(5, String.valueOf(dataNascimento));
            pstm.setInt(6, idade);
            pstm.setString(7, curso);
            pstm.setString(8, telefone);
            pstm.setString(9, endereco);
            pstm.setString(10, cpf);

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Usuário atualizado com sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
    }

    @Override
    public Result inativar(Usuario caroneiro) {
        try {
            Connection con = fabricaConexao.getConnection(); 
            
            PreparedStatement pstm = con.prepareStatement("UPDATE usuario set ativo=0 WHERE id=?");
            
            pstm.setInt(1, usuario.getId());

            pstm.execute();

            pstm.close();
            con.close();

            return Result.success("Usuario inativado com sucesso!");

        } catch(SQLException e){
            return Result.fail(e.getMessage());
        }
       
    }

    @Override
    public Usuario getUser() {
        return usuario;
    }    

}
