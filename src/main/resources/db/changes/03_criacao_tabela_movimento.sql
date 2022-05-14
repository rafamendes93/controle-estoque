--liquibase formatted sql

--changeset rafael:3
create table movimento_estoque
(
    id             serial
        constraint movimento_estoque_pk
            primary key,
    tipo_movimento char(1) not null,
    valor_venda    numeric(10, 2),
    data_venda     date,
    quantidade     int     not null,
    produto_id     int     not null
        constraint movimento_estoque_produto_id_fk
            references produto (id)
);

create unique index movimento_estoque_id_uindex
    on movimento_estoque (id);

INSERT INTO movimento_estoque (id, tipo_movimento, valor_venda, data_venda, quantidade, produto_id)
VALUES (1, 'E', null, null, 10, 1);
INSERT INTO movimento_estoque (id, tipo_movimento, valor_venda, data_venda, quantidade, produto_id)
VALUES (2, 'S', 2500.00, '2022-05-13', 1, 1);
INSERT INTO movimento_estoque (id, tipo_movimento, valor_venda, data_venda, quantidade, produto_id)
VALUES (3, 'S', 2500.00, '2022-05-13', 1, 1);
INSERT INTO movimento_estoque (id, tipo_movimento, valor_venda, data_venda, quantidade, produto_id)
VALUES (4, 'E', null, null, 3, 4);
INSERT INTO movimento_estoque (id, tipo_movimento, valor_venda, data_venda, quantidade, produto_id)
VALUES (5, 'E', null, null, 3, 4);
INSERT INTO movimento_estoque (id, tipo_movimento, valor_venda, data_venda, quantidade, produto_id)
VALUES (6, 'S', 1600.00, '2022-05-12', 1, 4);

--rollback drop table movimento_estoque;
