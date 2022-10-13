package com.vendas.vendasapi.exception;

public class PedidoNaoEncontradoException extends RuntimeException {
    public PedidoNaoEncontradoException(){
        super("Pedido nao encontrado.");
    }
}
