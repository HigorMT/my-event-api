-- Sequências para as tabelas
CREATE SEQUENCE IF NOT EXISTS tb_pessoa_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_endereco_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_cidade_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_evento_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_ingresso_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

-- Tabelas

DROP TABLE IF EXISTS tb_pessoa;
CREATE TABLE tb_pessoa
(
    id_pessoa BIGINT DEFAULT nextval('tb_pessoa_seq'::regclass) NOT NULL,
    nome      VARCHAR(255)                                      NOT NULL,
    cpf       VARCHAR(14)                                       NOT NULL,

    CONSTRAINT pk_tb_pessoa PRIMARY KEY (id_pessoa),
    CONSTRAINT uk_tb_pessoa UNIQUE (cpf)

);

DROP TABLE IF EXISTS tb_cidade;
CREATE TABLE tb_cidade
(
    id_cidade BIGINT DEFAULT nextval('tb_cidade_seq'::regclass) NOT NULL,
    nome      VARCHAR(255)                                      NOT NULL,
    estado    VARCHAR(2)                                        NOT NULL,

    CONSTRAINT pk_tb_cidade PRIMARY KEY (id_cidade)
);

DROP TABLE IF EXISTS tb_endereco;
CREATE TABLE tb_endereco
(
    id_endereco BIGINT DEFAULT nextval('tb_endereco_seq'::regclass) NOT NULL,
    cep         VARCHAR(9)                                          NOT NULL,
    id_cidade   BIGINT                                              NOT NULL,
    logradouro  VARCHAR(100)                                        NOT NULL,
    bairro      VARCHAR(50)                                         NOT NULL,
    complemento VARCHAR(255),
    numero      INT                                                 NOT NULL,

    CONSTRAINT pk_tb_endereco PRIMARY KEY (id_endereco),
    CONSTRAINT fk_tb_endereco_0 FOREIGN KEY (id_cidade) REFERENCES tb_cidade (id_cidade)
);

DROP TABLE IF EXISTS tb_evento;
CREATE TABLE tb_evento
(
    id_evento      BIGINT  DEFAULT nextval('tb_evento_seq'::regclass) NOT NULL,
    nome           VARCHAR(100)                                       NOT NULL,
    descricao      VARCHAR(500)                                       NOT NULL,
    id_organizador BIGINT                                             NOT NULL,
    data           TIMESTAMP                                          NOT NULL,
    capacidade     INT                                                NOT NULL,
    data_criacao   TIMESTAMP                                          NOT NULL,
    id_endereco    BIGINT                                             NOT NULL,
    status         INT4                                               NOT NULL,
    possui_espera  BOOLEAN DEFAULT FALSE                              NOT NULL,

    CONSTRAINT pk_tb_evento PRIMARY KEY (id_evento),
    CONSTRAINT fk_tb_evento_0 FOREIGN KEY (id_organizador) REFERENCES tb_usuario (id_usuario),
    CONSTRAINT fk_tb_evento_1 FOREIGN KEY (id_endereco) REFERENCES tb_endereco (id_endereco)
);

DROP TABLE IF EXISTS tb_ingresso;
CREATE TABLE tb_ingresso
(
    id_ingresso    BIGINT DEFAULT nextval('tb_ingresso_seq'::regclass) NOT NULL,
    id_usuario     BIGINT                                              NOT NULL,
    id_titular     BIGINT                                              NOT NULL,
    id_evento      BIGINT                                              NOT NULL,
    data_pagamento TIMESTAMP                                           NOT NULL,
    valor_ingresso NUMERIC(10, 2),
    data_criacao   TIMESTAMP                                           NOT NULL,
    status         INT                                                 NOT NULL,

    CONSTRAINT pk_tb_ingresso PRIMARY KEY (id_ingresso),
    CONSTRAINT fk_tb_ingresso_0 FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id_usuario),
    CONSTRAINT fk_tb_ingresso_1 FOREIGN KEY (id_titular) REFERENCES tb_pessoa (id_pessoa),
    CONSTRAINT fk_tb_ingresso_2 FOREIGN KEY (id_evento) REFERENCES tb_evento (id_evento)
);

-- Tabelas de Auditoria

DROP TABLE IF EXISTS tb_pessoa_aud;
CREATE TABLE tb_pessoa_aud
(
    id_pessoa BIGINT       NOT NULL,
    rev       BIGINT       NOT NULL,
    rev_type  INTEGER,
    nome      VARCHAR(255) NULL,
    cpf       VARCHAR(14)  NULL,

    CONSTRAINT pk_tb_pessoa_aud PRIMARY KEY (rev, id_pessoa)
);

DROP TABLE IF EXISTS tb_cidade_aud;
CREATE TABLE tb_cidade_aud
(
    id_cidade BIGINT       NOT NULL,
    rev       BIGINT       NOT NULL,
    rev_type  INTEGER,
    nome      VARCHAR(255) NULL,
    estado    VARCHAR(2)   NULL,

    CONSTRAINT pk_tb_cidadea_aud PRIMARY KEY (rev, id_cidade)
);

DROP TABLE IF EXISTS tb_endereco_aud;
CREATE TABLE tb_endereco_aud
(
    id_endereco BIGINT       NOT NULL,
    rev         BIGINT       NOT NULL,
    rev_type    INTEGER,
    cep         VARCHAR(9)   NULL,
    id_cidade   BIGINT       NULL,
    logradouro  VARCHAR(100) NULL,
    bairro      VARCHAR(50)  NULL,
    complemento VARCHAR(255),
    numero      INT          NULL,

    CONSTRAINT pk_tb_endereco_aud PRIMARY KEY (rev, id_endereco)
);

DROP TABLE IF EXISTS tb_evento_aud;
CREATE TABLE tb_evento_aud
(
    id_evento      BIGINT                NOT NULL,
    rev            BIGINT                NOT NULL,
    rev_type       INTEGER,
    nome           VARCHAR(100)          NULL,
    descricao      VARCHAR(500)          NULL,
    id_organizador BIGINT                NULL,
    data           TIMESTAMP             NULL,
    capacidade     INT                   NULL,
    data_criacao   TIMESTAMP             NULL,
    id_endereco    BIGINT                NULL,
    status         INT4                  NULL,
    possui_espera  BOOLEAN DEFAULT FALSE NULL,

    CONSTRAINT pk_tb_evento_aud PRIMARY KEY (rev, id_evento)
);

DROP TABLE IF EXISTS tb_ingresso_aud;
CREATE TABLE tb_ingresso_aud
(
    id_ingresso    BIGINT    NOT NULL,
    rev            BIGINT    NOT NULL,
    rev_type       INTEGER,
    id_usuario     BIGINT    NULL,
    id_titular     BIGINT    NULL,
    id_evento      BIGINT    NULL,
    data_pagamento TIMESTAMP NULL,
    valor_ingresso NUMERIC(10, 2),
    data_criacao   TIMESTAMP NULL,
    status         INT       NULL,

    CONSTRAINT pk_tb_ingresso_aud PRIMARY KEY (rev, id_ingresso)
);