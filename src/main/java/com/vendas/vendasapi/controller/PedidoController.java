package com.vendas.vendasapi.controller;

import com.vendas.vendasapi.dto.AtualizacaoStatusPedidoDTO;
import com.vendas.vendasapi.dto.InformacaoPedidoDTO;
import com.vendas.vendasapi.dto.InformacoesPedidoDTO;
import com.vendas.vendasapi.dto.PedidoDTO;
import com.vendas.vendasapi.entity.ItemPedido;
import com.vendas.vendasapi.entity.Pedido;
import com.vendas.vendasapi.enums.StatusPedido;
import com.vendas.vendasapi.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UUID save(@RequestBody PedidoDTO dto) {
        Pedido pedido = pedidoService.salvar(dto);
        return pedido.getId();
    }
    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable UUID id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map(pedido ->converter(pedido) )
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,"Pedido nao encontrado.")
                );

    }
    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable UUID id, @RequestBody AtualizacaoStatusPedidoDTO dto){
    String novoStatus = dto.getNovoStatus();
        pedidoService.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
    }
private InformacoesPedidoDTO converter(Pedido pedido){
        return InformacoesPedidoDTO.builder()
                .id(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .items(converter(pedido.getItems()))
                .build();

}
private List<InformacaoPedidoDTO> converter(List<ItemPedido>items){
        if(CollectionUtils.isEmpty(items)){
            return Collections.EMPTY_LIST;
        }
        return items.stream().map(itemPedido -> InformacaoPedidoDTO.builder().descricaoProduto(itemPedido.getProduto().getDescricao())
                .precoUnitario(itemPedido.getProduto().getPreco())
                .quantidade(itemPedido.getQuantidade())
                .build()
        ).collect(Collectors.toList());
}
}