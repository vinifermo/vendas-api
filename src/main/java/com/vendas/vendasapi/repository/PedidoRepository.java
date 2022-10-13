package com.vendas.vendasapi.repository;

import com.vendas.vendasapi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {
    @Query(" select p from Pedido p left join fetch p.items where p.id = :id ")
    Optional<Pedido> findByIdFetchItems(@Param("id") UUID id);
}