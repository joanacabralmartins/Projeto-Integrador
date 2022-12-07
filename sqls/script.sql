CREATE TABLE IF NOT EXISTS motorista (
    id INTEGER PRIMARY KEY, 
    cpf TEXT NOT NULL,
    nome TEXT NOT NULL,
    funcao_IFPR TEXT NOT NULL,
    senha TEXT NOT NULL,
    data_nascimento DATE NOT NULL,
    idade INTEGER NOT NULL,
    curso TEXT NULL,
    telefone INTEGER NULL,
    endereco TEXT NULL,
    carteira_motorista INTEGER NOT NULL 
);

CREATE TABLE IF NOT EXISTS carro (
    id INTEGER PRIMARY KEY, 
    placa TEXT NOT NULL,
    modelo TEXT NOT NULL,
    cor TEXT NOT NULL,
    cpf_motorista TEXT NOT NULL,
    CONSTRAINT carro_FK_motorista FOREIGN KEY (id) REFERENCES motorista(id)
);