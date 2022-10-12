package com.vendas.vendasapi.service;

import com.vendas.vendasapi.dto.PedidoDTO;
import com.vendas.vendasapi.entity.Pedido;

import java.util.UUID;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Pedido getPedidoById(UUID id);
}