CREATE TABLE IF NOT EXISTS usuario (
    id INTEGER PRIMARY KEY, 
    ativo TINYINT NOT NULL,
    cpf TEXT NOT NULL,
    nome TEXT NOT NULL,
    funcao_IFPR TEXT NOT NULL,
    senha TEXT NOT NULL,
    data_nascimento TEXT NOT NULL,
    idade INTEGER NOT NULL,
    curso TEXT NULL,
    telefone TEXT NULL,
    endereco TEXT NULL
);

CREATE TABLE IF NOT EXISTS motorista (
    id INTEGER PRIMARY KEY, 
    ativo TINYINT NOT NULL,
    cpf TEXT NOT NULL,
    nome TEXT NOT NULL,
    funcao_IFPR TEXT NOT NULL,
    senha TEXT NOT NULL,
    data_nascimento TEXT NOT NULL,
    idade INTEGER NOT NULL,
    curso TEXT NULL,
    telefone TEXT NULL,
    endereco TEXT NULL,
    carteira_motorista TEXT NOT NULL 
);

CREATE TABLE IF NOT EXISTS carro (
    id INTEGER PRIMARY KEY, 
    placa TEXT NOT NULL,
    modelo TEXT NOT NULL,
    cor TEXT NOT NULL,
    cpf_motorista TEXT NOT NULL,
    ativo TINYINT NOT NULL,
    CONSTRAINT carro_FK_motorista FOREIGN KEY(cpf_motorista) REFERENCES motorista(cpf)
);

CREATE TABLE IF NOT EXISTS solicitacao (
    id INTEGER PRIMARY KEY, 
    id_usuario INTEGER NOT NULL, 
    id_motorista INTEGER NOT NULL, 
    id_carona INTEGER NOT NULL, 
    dataHora_Solicitacao TEXT NOT NULL, 
    dataHora_Resposta TEXT NULL, 
    dataHora_Remocao TEXT NULL, 
    dataHora_Cancelamento TEXT NULL, 
    status INTEGER NOT NULL,
    CONSTRAINT solicitacao_FK_id_usuario FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    CONSTRAINT solicitacao_FK_id_motorista FOREIGN KEY(id_motorista) REFERENCES motorista(cpf),
    CONSTRAINT solicitacao_FK_id_carona FOREIGN KEY(id_carona) REFERENCES carona(id)
);

CREATE TABLE IF NOT EXISTS carona (
    id INTEGER PRIMARY KEY, 
    id_motorista INTEGER NOT NULL, 
    horarioSaida TIME NOT NULL, 
    quantidadeLugares INTEGER NOT NULL, 
    lugaresDisponiveis INTEGER NOT NULL, 
    ativo TINYINT NOT NULL,
    id_origem INTEGER NOT NULL, 
    id_destino INTEGER NOT NULL, 
    destino TEXT NOT NULL, 
    origem TEXT NOT NULL, 
    dataCadastro DATE NOT NULL, 
    data DATE NOT NULL, 
    dataRemocao DATE NULL, 
    dataCancelamento DATE NULL, 
    CONSTRAINT carona_FK_idMotorista FOREIGN KEY(id_motorista) REFERENCES motorista(id),
    CONSTRAINT carona_FK_id_origem FOREIGN KEY(id_origem) REFERENCES ponto(id),
    CONSTRAINT carona_FK_id_destino FOREIGN KEY(id_destino) REFERENCES ponto(id)
);

CREATE TABLE IF NOT EXISTS ponto (
    id INTEGER PRIMARY KEY, 
    id_carona INTEGER NOT NULL, 
    descricao TEXT NOT NULL,
    ativo TINYINT NOT NULL,
    CONSTRAINT ponto_FK_id_carona FOREIGN KEY(id_carona) REFERENCES carona(id)
);