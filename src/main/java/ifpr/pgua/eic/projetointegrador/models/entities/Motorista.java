package ifpr.pgua.eic.projetointegrador.models.entities;

public class Motorista extends Usuario {

  private int carteira_motorista;
  private double raio_atuacao;

  public Motorista(int cpf, String nome, String funcao_IFPR, String senha, int idade, String curso, int telefone,
      String endereco, int carteira_motorista, double raio_atuacao) {
    super(cpf, nome, funcao_IFPR, senha, idade, curso, telefone, endereco);

    this.carteira_motorista = carteira_motorista;
    this.raio_atuacao = raio_atuacao;
  }

  public int getCarteira_motorista() {
    return carteira_motorista;
  }

  public void setCarteira_motorista(int carteira_motorista) {
    this.carteira_motorista = carteira_motorista;
  }

  public double getRaio_atuacao() {
    return raio_atuacao;
  }

  public void setRaio_atuacao(double raio_atuacao) {
    this.raio_atuacao = raio_atuacao;
  }

}
