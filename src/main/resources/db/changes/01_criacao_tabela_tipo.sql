--liquibase formatted sql

--changeset rafael:1
create table tipo_produto
(
    id        serial
        constraint tipo_produto_pk
            primary key,
    descricao varchar not null
);
create unique index tipo_produto_id_uindex
    on tipo_produto (id);

INSERT INTO tipo_produto (id, descricao)
VALUES (1, 'Eletrônico');
INSERT INTO tipo_produto (id, descricao)
VALUES (2, 'Eletrodomêstico');
INSERT INTO tipo_produto (id, descricao)
VALUES (3, 'Móvel');
--rollback drop table tipo_produto;