package com.faturamentofrete.frete.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="tb_custofixo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustoFixo {

        @Id
        private int id = 1;

        private BigDecimal financiamento;

        private BigDecimal reserva;

        private BigDecimal trocadeoleo;

        private BigDecimal outros;
}
