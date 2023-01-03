package ifpr.pgua.eic.projetointegrador.models.entities;

public class Carro {

  private int id;
  private String placa;
  private String modelo;
  private String cor;
  private int id_motorista;
  private boolean ativo;

  public Carro(int id, String placa, String modelo, String cor, int id_motorista, boolean ativo) {
    this.id = id;
    this.placa = placa;
    this.modelo = modelo;
    this.cor = cor;
    this.id_motorista = id_motorista;
    this.ativo = ativo;
  }

  public Carro(String placa, String modelo, String cor, int id_motorista) {
    this(-1, placa, modelo, cor, id_motorista, true);
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

  public int getId__motorista() {
    return id_motorista;
  }

  public void setId_motorista(int id_motorista) {
    this.id_motorista = id_motorista;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

}