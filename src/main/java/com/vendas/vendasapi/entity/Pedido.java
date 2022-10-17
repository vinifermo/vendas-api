package com.vendas.vendasapi.entity;

import com.vendas.vendasapi.enums.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_pedido", schema = "crud")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", precision = 20, scale = 2 )
    private BigDecimal total;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> items;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPedido status;
}