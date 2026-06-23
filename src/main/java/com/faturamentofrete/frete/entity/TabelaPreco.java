package com.faturamentofrete.frete.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_Preco")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TabelaPreco {

    @Id
    private Long id = 1L;

    private BigDecimal am;

    private BigDecimal pm;

    private BigDecimal sd;

    private BigDecimal sdd;

}
