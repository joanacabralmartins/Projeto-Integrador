package ifpr.pgua.eic.projetointegrador.models.entities;

import java.sql.Date;

public class Motorista extends Usuario {

  private String carteira_motorista;

  public Motorista(int id, int ativo, String cpf, String nome, String funcao_IFPR, String senha, Date data_nascimento, int idade, String curso, String telefone,
      String endereco, String carteira_motorista) {
    super(id, ativo, cpf, nome, funcao_IFPR, senha, data_nascimento, idade, curso, telefone, endereco);

    this.carteira_motorista = carteira_motorista;
  }

  public String getCarteira_motorista() {
    return carteira_motorista;
  }

  public void setCarteira_motorista(String carteira_motorista) {
    this.carteira_motorista = carteira_motorista;
  }
}
