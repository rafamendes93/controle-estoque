--liquibase formatted sql

--changeset rafael:2
create table produto
(
    id          serial
        constraint produto_pk
            primary key,
    descricao   varchar        not null,
    valor_custo numeric(10, 2) not null,
    tipo_id     int            not null
        constraint produto_tipo_id_fk
            references tipo_produto (id)
);

create unique index produto_id_uindex
    on produto (id);

INSERT INTO produto (id, descricao, valor_custo, tipo_id)
VALUES (1, 'Notebook', 1650.00, 1);
INSERT INTO produto (id, descricao, valor_custo, tipo_id)
VALUES (2, 'Maquina de lavar roupa', 2300.00, 2);
INSERT INTO produto (id, descricao, valor_custo, tipo_id)
VALUES (3, 'Sofá', 1200.00, 3);
INSERT INTO produto (id, descricao, valor_custo, tipo_id)
VALUES (4, 'Smartphone', 1150.00, 1);

CREATE SEQUENCE sequence_produto START 5;

--rollback drop table produto;
