package ifpr.pgua.eic.projetointegrador.models.entities;

import java.sql.Date;

public class Carona {
    
    private int id;
    private int id_motorista;
    private String cpfMotorista;
    private int horarioSaida;
    private int quantidadeLugares;
    private int lugaresDisponiveis;
    private boolean status;
    private String origem;
    private String destino;
    private Date dataCadastro;
    private Date data;
    private Date dataRemocao;
    private Date dataCancelamento;


    public Carona(int id, int id_motorista, String cpfMotorista, int horarioSaida, int quantidadeLugares,
            int lugaresDisponiveis, boolean status, String origem, String destino, Date dataCadastro, Date data,
            Date dataRemocao, Date dataCancelamento) {
        this.id = id;
        this.id_motorista = id_motorista;
        this.cpfMotorista = cpfMotorista;
        this.horarioSaida = horarioSaida;
        this.quantidadeLugares = quantidadeLugares;
        this.lugaresDisponiveis = lugaresDisponiveis;
        this.status = status;
        this.origem = origem;
        this.destino = destino;
        this.dataCadastro = dataCadastro;
        this.data = data;
        this.dataRemocao = dataRemocao;
        this.dataCancelamento = dataCancelamento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(int id_motorista) {
        this.id_motorista = id_motorista;
    }

    public String getCpfMotorista() {
        return cpfMotorista;
    }

    public void setCpfMotorista(String cpfMotorista) {
        this.cpfMotorista = cpfMotorista;
    }

    public int getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(int horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public int getQuantidadeLugares() {
        return quantidadeLugares;
    }

    public void setQuantidadeLugares(int quantidadeLugares) {
        this.quantidadeLugares = quantidadeLugares;
    }

    public int getLugaresDisponiveis() {
        return lugaresDisponiveis;
    }

    public void setLugaresDisponiveis(int lugaresDisponiveis) {
        this.lugaresDisponiveis = lugaresDisponiveis;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
     
    public String getOrigem() {
        return origem;
    }
    public void setOrigem(String origem) {
        this.origem = origem;
    }
    public String getDestino() {
        return destino;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public Date getDataCadastro() {
        return dataCadastro;
    }
    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    public Date getData() {
        return data;
    }
    public void setData(Date data) {
        this.data = data;
    }
    public Date getDataRemocao() {
        return dataRemocao;
    }
    public void setDataRemocao(Date dataRemocao) {
        this.dataRemocao = dataRemocao;
    }
    public Date getDataCancelamento() {
        return dataCancelamento;
    }
    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }

}
