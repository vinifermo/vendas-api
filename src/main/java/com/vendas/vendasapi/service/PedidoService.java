package com.vendas.vendasapi.service;

import com.vendas.vendasapi.dto.PedidoDTO;
import com.vendas.vendasapi.entity.Pedido;
import com.vendas.vendasapi.enums.StatusPedido;

import java.util.Optional;
import java.util.UUID;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(UUID id);

    void atualizarStatus(UUID id, StatusPedido statusPedido);

}