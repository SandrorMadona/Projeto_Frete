package com.faturamentofrete.frete.entity;


import com.faturamentofrete.frete.enums.Turno;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable // significa embutivel, é como se essa classe fosse uma organizadora, ela nao é uma tabela principal
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoRealizado {

    // A anotação do Enum vem para cá, para garantir que salve "AM", "PM" ou "SD" no banco.
    @Enumerated(EnumType.STRING)
    private Turno turno;

    // O booleano que indica se esse turno específico teve a taxa extra.
    private boolean sdd;

}
