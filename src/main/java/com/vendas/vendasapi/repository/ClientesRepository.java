package com.vendas.vendasapi.repository;

import com.vendas.vendasapi.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientesRepository extends JpaRepository<Cliente, UUID> {

}