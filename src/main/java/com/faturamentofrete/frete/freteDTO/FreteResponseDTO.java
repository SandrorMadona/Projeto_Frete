package com.faturamentofrete.frete.freteDTO;

import com.faturamentofrete.frete.entity.Frete;
import com.faturamentofrete.frete.enums.Turno;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FreteResponseDTO(
        Long id,
        LocalDate dataServico,
        List<Turno> turnos,
        BigDecimal faturamento,
        BigDecimal gasto,
        boolean isDobra,
        boolean sdd
) {
        public FreteResponseDTO(Frete frete){
            this(
                    frete.getId(),
                    frete.getDataServico(),
                    frete.getTurnos(),
                    frete.getFaturamento(),
                    frete.getGasto(),
                    frete.isDobra(),
                    frete.isSdd()
            );

        }


}
