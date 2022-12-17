CREATE TABLE IF NOT EXISTS usuario (
    id INTEGER PRIMARY KEY, 
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
    CONSTRAINT carro_FK_motorista FOREIGN KEY(cpf_motorista) REFERENCES motorista(cpf)
);

CREATE TABLE IF NOT EXISTS carona (
    id INTEGER PRIMARY KEY, 
    placa TEXT NOT NULL,
    modelo TEXT NOT NULL, 
    cor TEXT NOT NULL, 
    cpf_motorista TEXT NOT NULL,
    CONSTRAINT carona_FK_motorista FOREIGN KEY(cpf_motorista) REFERENCES motorista(cpf)
);