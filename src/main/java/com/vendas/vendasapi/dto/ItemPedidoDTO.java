package com.vendas.vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {

    private UUID produto;
    private Integer quantidade;
}
