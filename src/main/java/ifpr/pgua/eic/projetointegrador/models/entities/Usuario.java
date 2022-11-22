package ifpr.pgua.eic.projetointegrador.models.entities;

public class Usuario {

  private int cpf;
  private String nome;
  private String funcao_IFPR;
  private String senha;
  private int idade;
  private String curso;
  private int telefone;
  private String endereco;

  public Usuario(int cpf, String nome, String funcao_IFPR, String senha,
      int idade, String curso, int telefone, String endereco) {

    this.cpf = cpf;
    this.nome = nome;
    this.funcao_IFPR = funcao_IFPR;
    this.senha = senha;
    this.idade = idade;
    this.curso = curso;
    this.telefone = telefone;
    this.endereco = endereco;
  }

  public int getCpf() {
    return cpf;
  }

  public void setCpf(int cpf) {
    this.cpf = cpf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getFuncao_IFPR() {
    return funcao_IFPR;
  }

  public void setFuncao_IFPR(String funcao_IFPR) {
    this.funcao_IFPR = funcao_IFPR;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public String getCurso() {
    return curso;
  }

  public void setCurso(String curso) {
    this.curso = curso;
  }

  public int getTelefone() {
    return telefone;
  }

  public void setTelefone(int telefone) {
    this.telefone = telefone;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

}