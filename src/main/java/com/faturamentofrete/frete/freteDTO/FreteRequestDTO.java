package com.faturamentofrete.frete.freteDTO;

import java.math.BigDecimal;
import java.time.LocalDate;

public record FreteRequestDTO(
        LocalDate dataServico,
        String turnos,
        BigDecimal faturamento,
        BigDecimal gasto,
        boolean isDobra
) {
}
