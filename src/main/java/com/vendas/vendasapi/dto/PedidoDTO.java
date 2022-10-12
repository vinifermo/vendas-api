package com.vendas.vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    private UUID cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO>items;

}
