package ifpr.pgua.eic.projetointegrador.models.entities;

public class Carro {

  private int id;
  private String placa;
  private String modelo;
  private String cor;
  private String cpf_motorista;
  private boolean ativo;

  public Carro(int id, String placa, String modelo, String cor, String cpf_motorista, boolean ativo) {
    this.id = id;
    this.placa = placa;
    this.modelo = modelo;
    this.cor = cor;
    this.cpf_motorista = cpf_motorista;
    this.ativo = ativo;
  }

  public Carro(String placa, String modelo, String cor, String cpf_motorista) {
    this(-1, placa, modelo, cor, cpf_motorista, true);
}

  public int getId() {
    return id;
  } 

  public void setId(int id) {
    this.id = id;
  }

  public String getPlaca() {
    return placa;
  }

  public void setPlaca(String placa) {
    this.placa = placa;
  }

  public String getModelo() {
    return modelo;
  }

  public void setModelo(String modelo) {
    this.modelo = modelo;
  }

  public String getCor() {
    return cor;
  }

  public void setCor(String cor) {
    this.cor = cor;
  }

  public String getCpf_motorista() {
    return cpf_motorista;
  }

  public void setCpf_motorista(String cpf_motorista) {
    this.cpf_motorista = cpf_motorista;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

}