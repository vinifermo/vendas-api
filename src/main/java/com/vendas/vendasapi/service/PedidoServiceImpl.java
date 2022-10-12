package com.vendas.vendasapi.service;

import com.vendas.vendasapi.dto.ItemPedidoDTO;
import com.vendas.vendasapi.dto.PedidoDTO;
import com.vendas.vendasapi.entity.Cliente;
import com.vendas.vendasapi.entity.ItemPedido;
import com.vendas.vendasapi.entity.Pedido;
import com.vendas.vendasapi.entity.Produto;
import com.vendas.vendasapi.exception.RegraNegocioException;
import com.vendas.vendasapi.repository.ClientesRepository;
import com.vendas.vendasapi.repository.ItemPedidoRepository;
import com.vendas.vendasapi.repository.PedidoRepository;
import com.vendas.vendasapi.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClientesRepository clientesRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    public Pedido salvar(PedidoDTO dto) {
        UUID idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Codigo de cliente invalido!"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedido = converterItems(pedido, dto.getItems());
        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedido);
        pedido.setItems(itemPedido);

        return pedido;
    }

    public Pedido getPedidoById(UUID id) {
        pedidoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido nao encontrado!"));
        Pedido pedidoSalvo = new Pedido();
        return pedidoSalvo;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items) {
        if (items.isEmpty()) {
            throw new RegraNegocioException("Nao e possivel realizar um pedido sem itens.");
        }
        return items
                .stream()
                .map(itemPedidoDTO -> {
                    UUID idProduto = itemPedidoDTO.getProduto();
                    Produto produto = produtoRepository.findById(idProduto).orElseThrow(
                            () -> new RegraNegocioException("Codigo de produto invalido!: " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(itemPedidoDTO.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}