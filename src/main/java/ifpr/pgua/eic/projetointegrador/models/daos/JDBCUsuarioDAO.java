package ifpr.pgua.eic.projetointegrador.models.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
                                    
            PreparedStatement pstm = con.prepareStatement("INSERT INTO usuario(cpf,nome,funcao_IFPR,senha,data_nascimento,idade,curso,telefone,endereco) VALUES (?,?,?,?,?,?,?,?,?)");
            
            pstm.setString(1, caroneiro.getCpf());
            pstm.setString(2, caroneiro.getNome());
            pstm.setString(3, caroneiro.getFuncao_IFPR());
            pstm.setString(4, caroneiro.getSenha());
            pstm.setString(5, String.valueOf(caroneiro.getData_nascimento()));
            pstm.setInt(6, caroneiro.getIdade());
            pstm.setString(7, caroneiro.getCurso());
            pstm.setString(8, caroneiro.getTelefone());
            pstm.setString(9, caroneiro.getEndereco());

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
    public Result validarLogin(String cpf, String senha) {
        try {
            Connection con = fabricaConexao.getConnection();

            PreparedStatement pstm = con.prepareStatement("SELECT * FROM usuario WHERE cpf=? and senha=?"); 

            pstm.setString(1, cpf);
            pstm.setString(2, senha);

            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                String nome = rs.getString("nome");
                String funcao = rs.getString("funcao_IFPR");
                String curso = rs.getString("curso");
                String tel = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                int idade = rs.getInt("idade");
                String dataNascimento = rs.getString("data_nascimento");

                Date dtDataNascimento = Date.valueOf(dataNascimento);

                usuario = new Usuario(cpf, nome, funcao, senha, dtDataNascimento, idade, curso, tel, endereco);
                
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
    public Usuario getUser() {
        return usuario;
    }    
}
