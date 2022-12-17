package ifpr.pgua.eic.projetointegrador.models.entities;

import java.time.LocalDateTime;

public class SolicitacaoCarona {
    
    private int id;
    private int id_usuario;
    private int id_motorista;
    private int id_carona;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataResposta;
    private LocalDateTime dataCancelamento;

    
    public SolicitacaoCarona(int id, int id_usuario, int id_motorista, int id_carona, LocalDateTime dataSolicitacao,
            LocalDateTime dataResposta) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_motorista = id_motorista;
        this.id_carona = id_carona;
        this.dataSolicitacao = dataSolicitacao;
        this.dataResposta = dataResposta;
    }

    public SolicitacaoCarona(int id, int id_usuario, int id_motorista, int id_carona, LocalDateTime dataSolicitacao,
            LocalDateTime dataResposta, LocalDateTime dataCancelamento) {
        this.id = id;
        this.id_usuario = id_usuario;
        this.id_motorista = id_motorista;
        this.id_carona = id_carona;
        this.dataSolicitacao = dataSolicitacao;
        this.dataResposta = dataResposta;
        this.dataCancelamento = dataCancelamento;
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

    public LocalDateTime getDataSolicitacao() {
        return dataSolicitacao;
    }
    public void setDataSolicitacao(LocalDateTime dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public LocalDateTime getDataResposta() {
        return dataResposta;
    }

    public void setDataResposta(LocalDateTime dataResposta) {
        this.dataResposta = dataResposta;
    }

    public LocalDateTime getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(LocalDateTime dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }
}
