package ifpr.pgua.eic.projetointegrador.models.entities;

import java.sql.Date;

public class Motorista extends Usuario {

  private int carteira_motorista;

  public Motorista(String cpf, String nome, String funcao_IFPR, String senha, Date data_nascimento, int idade, String curso, int telefone,
      String endereco, int carteira_motorista) {
    super(cpf, nome, funcao_IFPR, senha, data_nascimento, idade, curso, telefone, endereco);

    this.carteira_motorista = carteira_motorista;
  }

  public int getCarteira_motorista() {
    return carteira_motorista;
  }

  public void setCarteira_motorista(int carteira_motorista) {
    this.carteira_motorista = carteira_motorista;
  }
}
