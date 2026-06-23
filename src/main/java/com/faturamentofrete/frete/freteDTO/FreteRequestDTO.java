package com.faturamentofrete.frete.freteDTO;

import com.faturamentofrete.frete.enums.Turno;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FreteRequestDTO(
        LocalDate dataServico,
        @NotEmpty
        List<Turno> turnos,
        BigDecimal faturamento,
        BigDecimal gasto,
        boolean sdd
        //nao em boolean isDobra no DTO porque se o front enviar isDobra como false sendo que tem mais de dois itens na list<turno>, isso cria um problema
) {
}
