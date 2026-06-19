package com.faturamentofrete.frete.freteDTO;

import com.faturamentofrete.frete.entity.Frete;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FreteResponseDTO(
        Long id,
        LocalDate dataServico,
        String turnos,
        BigDecimal faturamento,
        BigDecimal gasto,
        boolean isDobra
) {
        public FreteResponseDTO(Frete frete){
            this(
                    frete.getId(),
                    frete.getDataServico(),
                    frete.getTurnos(),
                    frete.getFaturamento(),
                    frete.getGasto(),
                    frete.isDobra()
            );

        }


}
