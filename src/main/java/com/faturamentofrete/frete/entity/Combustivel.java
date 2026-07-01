package com.faturamentofrete.frete.entity;

import com.faturamentofrete.frete.enums.TipoCombustivel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Table(name = "tb_Combustivel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Combustivel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal valorTotal;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    // Anotação para que salve o enum como string e nao como numeral
    @Enumerated(EnumType.STRING)
    private TipoCombustivel tipoCombustivel;

}
