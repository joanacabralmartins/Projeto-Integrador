package ifpr.pgua.eic.projetointegrador.models.entities;

public class PontoParada {

  private int id;
  private int id_carona;
  private String descricao;
  private boolean ativo;//na listagem em tela, comparar id com getDestino e getOrigem para não listar 
  
  public PontoParada(int id, int id_carona, String descricao, boolean ativo) {
    this.id = id;
    this.id_carona = id_carona;
    this.descricao = descricao;
    this.ativo = ativo;
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

  public boolean getAtivo() {
    return ativo;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

}