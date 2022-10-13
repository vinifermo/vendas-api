package com.vendas.vendasapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AtualizacaoStatusPedidoDTO {
    private String novoStatus;
}
