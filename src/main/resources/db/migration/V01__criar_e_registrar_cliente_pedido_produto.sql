CREATE TABLE tb_cliente
(
    id              UUID PRIMARY KEY,
    nome            VARCHAR(50) NOT NULL,
    cpf             VARCHAR(11)
);

CREATE TABLE tb_pedido
(
    id              UUID PRIMARY KEY,
    data_pedido     timestamp,
    total           numeric(20,2),
    status          varchar(20),
    cliente_id      UUID,
    foreign key (cliente_id) references tb_cliente(id)
);

CREATE TABLE tb_produto (
   id              UUID PRIMARY KEY,
    DESCRICAO VARCHAR(100),
    PRECO_UNITARIO NUMERIC(20,2)
);

CREATE TABLE ITEM_PEDIDO (
    id              UUID PRIMARY KEY,
    QUANTIDADE      INTEGER,
    pedido_id       UUID,
    produto_id      UUID,
    foreign key (pedido_id) references tb_pedido(id),
    foreign key (produto_id) references tb_produto(id)

);

