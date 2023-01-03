CREATE TABLE IF NOT EXISTS usuario (
    id int AUTO_INCREMENT, 
    ativo tinyint NOT NULL,
    cpf varchar(11) NOT NULL,
    nome varchar(255) NOT NULL,
    funcao_IFPR varchar(12) NOT NULL,
    senha varchar(255) NOT NULL,
    data_nascimento varchar(11) NOT NULL,
    idade int NOT NULL,
    curso varchar(50) NULL,
    telefone varchar(15) NULL,
    endereco varchar(50) NULL,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS motorista (
    id int AUTO_INCREMENT, 
    ativo tinyint NOT NULL,
    cpf varchar(11) NOT NULL,
    nome varchar(255) NOT NULL,
    funcao_IFPR varchar(12) NOT NULL,
    senha varchar(255) NOT NULL,
    data_nascimento varchar(11) NOT NULL,
    idade int NOT NULL,
    curso varchar(50) NULL,
    telefone varchar(15) NULL,
    endereco varchar(50) NULL,
    carteira_motorista varchar(255) NOT NULL,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS carro (
    id int AUTO_INCREMENT, 
    placa varchar(7) NOT NULL,
    modelo varchar(15) NOT NULL,
    cor varchar(35) NOT NULL,
    id_motorista int NOT NULL,
    ativo TINYINT NOT NULL,
    primary key(id),
    foreign key(id_motorista) REFERENCES motorista(id)
);

CREATE TABLE IF NOT EXISTS ponto (
    id int AUTO_INCREMENT, 
    id_carona int NOT NULL, 
    descricao varchar(255) NOT NULL,
    ativo tinyint NOT NULL,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS carona (
    id int AUTO_INCREMENT, 
    id_motorista int NOT NULL, 
    horarioSaida time NOT NULL, 
    quantidadeLugares int NOT NULL, 
    lugaresDisponiveis int NOT NULL, 
    ativo tinyint NOT NULL,
    id_origem int NOT NULL, 
    id_destino int NOT NULL, 
    destino varchar(250)  NOT NULL, 
    origem varchar(250)  NOT NULL, 
    dataCadastro DATE NOT NULL, 
    data DATE NOT NULL, 
    dataRemocao DATE NULL, 
    dataCancelamento DATE NULL, 
    primary key(id),
    FOREIGN KEY(id_motorista) REFERENCES motorista(id),
    FOREIGN KEY(id_origem) REFERENCES ponto(id),
    FOREIGN KEY(id_destino) REFERENCES ponto(id)
);

CREATE TABLE IF NOT EXISTS solicitacao (
    id int AUTO_INCREMENT, 
    id_usuario int NOT NULL, 
    id_motorista int NOT NULL, 
    id_carona int NOT NULL, 
    dataHora_Solicitacao varchar(22) NOT NULL, 
    dataHora_Resposta varchar(22) NULL, 
    dataHora_Remocao varchar(22) NULL, 
    dataHora_Cancelamento varchar(22) NULL, 
    status int NOT NULL,
    primary key(id),
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    FOREIGN KEY(id_motorista) REFERENCES motorista(id),
    FOREIGN KEY(id_carona) REFERENCES carona(id)
);

alter table ponto add foreign key(id_carona) REFERENCES carona(id);
