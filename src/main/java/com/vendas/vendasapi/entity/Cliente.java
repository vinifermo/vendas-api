package com.vendas.vendasapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_cliente", schema = "crud")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotEmpty (message = "Campo nome é obrigatorio")
    private String nome;

    @NotEmpty(message = "Campo CPF é obrigatorio")
    @CPF(message = "Informe um CPF válido.")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy ="cliente")
    private List<Pedido> pedidos;
}