--liquibase formatted sql

--changeset rafael:3
create table movimento_estoque
(
    id             serial
        constraint movimento_estoque_pk
            primary key,
    tipo_movimento char(1)        not null,
    valor_venda    numeric(10, 2) not null,
    data_venda     date           not null,
    quantidade     int            not null,
    produto_id     int            not null
        constraint movimento_estoque_produto_id_fk
            references produto (id)
);

create unique index movimento_estoque_id_uindex
    on movimento_estoque (id);
--rollback drop table movimento_estoque;
