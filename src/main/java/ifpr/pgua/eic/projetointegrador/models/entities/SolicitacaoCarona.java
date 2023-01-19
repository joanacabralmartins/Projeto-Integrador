package ifpr.pgua.eic.projetointegrador.models.entities;

import java.time.LocalDateTime;

public class SolicitacaoCarona {
    
    private int id;
    private int id_usuario;
    private int id_motorista;
    private int id_carona;
    private LocalDateTime dataHora_Solicitacao;
    private LocalDateTime dataHora_Resposta;
    private LocalDateTime dataHora_Remocao;
    private LocalDateTime dataHora_Cancelamento;
    private int status;//0 = pendente, 1 = recusada, 2 = cancelada, 3 = aceita, 4 = aceitada mas passageiro removido

    public SolicitacaoCarona(int id, int id_usuario, int id_motorista, int id_carona, 
                            LocalDateTime dataHora_Solicitacao, int status) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_motorista = id_motorista;
        this.id_carona = id_carona;
        this.dataHora_Solicitacao = dataHora_Solicitacao;
        this.status= status;
    }

    public SolicitacaoCarona(int id, int id_usuario, int id_motorista, int id_carona, 
                            LocalDateTime dataHora_Solicitacao, LocalDateTime dataHora_Resposta, LocalDateTime dataHora_Remocao, LocalDateTime dataHora_Cancelamento, 
                            int status) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_motorista = id_motorista;
        this.id_carona = id_carona;
        this.dataHora_Solicitacao = dataHora_Solicitacao;
        this.dataHora_Resposta = dataHora_Resposta;
        this.dataHora_Remocao = dataHora_Remocao;
        this.dataHora_Cancelamento = dataHora_Cancelamento;
        this.status= status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_motorista() {
        return id_motorista;
    }

    public void setId_motorista(int id_motorista) {
        this.id_motorista = id_motorista;
    }

    public int getId_carona() {
        return id_carona;
    }

    public void setId_carona(int id_carona) {
        this.id_carona = id_carona;
    }

    public LocalDateTime getDataHora_Solicitacao() {
        return dataHora_Solicitacao;
    }
    public void setDataHora_Solicitacao(LocalDateTime dataHora_Solicitacao) {
        this.dataHora_Solicitacao = dataHora_Solicitacao;
    }

    public LocalDateTime getDataHora_Resposta() {
        return dataHora_Resposta;
    }

    public void setDataHora_Resposta(LocalDateTime dataHora_Resposta) {
        this.dataHora_Resposta = dataHora_Resposta;
    }

    public LocalDateTime getDataHora_Remocao() {
        return dataHora_Remocao;
    }

    public void setDataRemocao(LocalDateTime dataHora_Remocao) {
        this.dataHora_Remocao = dataHora_Remocao;
    }

    public LocalDateTime getDataCancelamento() {
        return dataHora_Cancelamento;
    }

    public void setDataHora_Cancelamento(LocalDateTime dataHora_Cancelamento) {
        this.dataHora_Cancelamento = dataHora_Cancelamento;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
