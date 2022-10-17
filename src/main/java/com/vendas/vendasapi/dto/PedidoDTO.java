package com.vendas.vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {

    @NotNull(message = "Informe o código do cliente")
    private UUID cliente;

    @NotNull(message = "Campo total do pedido é obrigatorio.")
    private BigDecimal total;

    private List<ItemPedidoDTO> items;

}
