package com.vendas.vendasapi.repository;

import com.vendas.vendasapi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {


}