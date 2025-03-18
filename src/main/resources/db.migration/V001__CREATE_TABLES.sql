CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- Sequências para as tabelas
CREATE SEQUENCE IF NOT EXISTS tb_revinfo_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_permissao_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_usuario_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;

CREATE SEQUENCE IF NOT EXISTS tb_perfil_seq
    INCREMENT BY 1
    MINVALUE 1
    START WITH 1
    CACHE 1
    NO CYCLE;


-- Tabela tb_permissao
create table tb_permissao
(
    id_permissao BIGINT default nextval('tb_permissao_seq'::regclass) NOT NULL,
    permissao    INTEGER                                              NOT NULL,
    descricao    TIMESTAMP(255)                                       NOT NULL,
    PRIMARY KEY (id_permissao),
    UNIQUE (permissao)
);

-- Tabela tb_perfil
create table tb_perfil
(
    id_perfil BIGINT       NOT NULL DEFAULT nextval('tb_perfil_seq'),
    nome      VARCHAR(100) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    padrao    BOOLEAN      NOT NULL DEFAULT FALSE,
    status    SMALLINT     NOT NULL DEFAULT 1,
    PRIMARY KEY (id_perfil)
);

-- Tabela tb_perfil_permissao
create table tb_perfil_permissao
(
    id_perfil    BIGINT NOT NULL,
    id_permissao BIGINT NOT NULL,
    UNIQUE (id_perfil, id_permissao),
    FOREIGN KEY (id_perfil) REFERENCES tb_perfil (id_perfil),
    FOREIGN KEY (id_permissao) REFERENCES tb_permissao (id_permissao)
);

-- Tabela tb_usuario
CREATE TABLE tb_usuario
(
    id_usuario       BIGINT       NOT NULL DEFAULT nextval('tb_usuario_seq'),
    nome             VARCHAR(100) NOT NULL,
    credencial       VARCHAR(50)  NOT NULL,
    senha            VARCHAR(255) NOT NULL,
    ultimo_login     TIMESTAMP    NOT NULL,
    email            VARCHAR(45)           DEFAULT NULL,
    status           SMALLINT     NOT NULL DEFAULT 1,
    id_perfil        BIGINT       NOT NULL,
    data_cadastro    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_atualizacao TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_usuario),
    UNIQUE (credencial),
    FOREIGN KEY (id_perfil) REFERENCES tb_perfil (id_perfil)
);

-- Tabela revinfo
create table revinfo
(
    rev           BIGINT  NOT NULL DEFAULT nextval('tb_revinfo_seq'),
    data_cadastro TIMESTAMP(6),
    entidade      VARCHAR(255),
    ip            VARCHAR(255),
    rev_type      INTEGER,
    id_usuario    BIGINT,
    is_device     BOOLEAN NOT NULL DEFAULT FALSE,
    is_system     BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (rev),
    FOREIGN KEY (id_usuario) REFERENCES tb_usuario (id_usuario)
);

INSERT INTO tb_perfil(id_perfil, nome, descricao, padrao, status)
VALUES (nextval('tb_perfil_seq'), 'Admin', 'Perfil de administrador do sistema.', true, 1);

INSERT INTO tb_usuario(id_usuario, nome, credencial, senha, ultimo_login, email, data_cadastro, data_atualizacao, id_perfil)
values (nextval('tb_usuario_seq'), 'Higor', 'HigorMT', '$2a$10$730XEnzx.5Dcql2Xul1GAOEdxCl28Arn.Nz8Zr2dACpI4Wbb..oG6',
        CURRENT_TIMESTAMP, 'higor.marino@hotmail.com', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1);
