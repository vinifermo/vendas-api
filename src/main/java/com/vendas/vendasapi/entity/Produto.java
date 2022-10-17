package com.vendas.vendasapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_produto", schema = "crud")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotEmpty(message = "Campo descrição é obrigatorio.")
    private String descricao;

    @NotNull(message = "Campo preço é obrigatorio!")
    @Column(name = "preco_unitario")
    private BigDecimal preco;
}