package ifpr.pgua.eic.projetointegrador.models.entities;

public class PontoParada {

  private int id;
  private int id_carona;
  private String descricao;
  private int status;//0=inativo 1=ativo 2=origem/destino
  
  public PontoParada(int id, int id_carona, String descricao, int status) {
    this.id = id;
    this.descricao = descricao;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId_carona() {
    return id_carona;
  }

  public void setId_carona(int id_carona) {
    this.id_carona = id_carona;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

}