CREATE TABLE IF NOT EXISTS motorista (
    cpf INTEGER PRIMARY KEY,
    nome TEXT NOT NULL,
    funcao_IFPR TEXT NOT NULL,
    senha TEXT NOT NULL,
    idade INTEGER NOT NULL,
    curso TEXT NOT NULL,
    telefone INTEGER NOT NULL,
    endereco TEXT NOT NULL,
    carteira_motorista INTEGER NOT NULL,
    raio_atuacao INTEGER 
);

CREATE TABLE IF NOT EXISTS carro (
    placa TEXT PRIMARY KEY,
    modelo TEXT NOT NULL,
    cor TEXT NOT NULL,
    cpf_motorista INTEGER NOT NULL
);