package com.faturamentofrete.frete.entity;

import com.faturamentofrete.frete.enums.Turno;
import com.faturamentofrete.frete.freteDTO.FreteRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_fretes")
@Getter
@Setter
@NoArgsConstructor //Cria um construtor completamente vazio na sua classe. ex: public Frete() { }, primeiro criam o objeto "em branco" usando esse construtor vazio e, só depois, vão "injetando" os dados dentro dele (usando os setters).
@AllArgsConstructor //Cria um construtor que pede um valor para cada um dos atributos que existem na sua classe. ex: public Frete(Long id, String destino, Double preco) { this.id = id; this.destino = destino; ... }
public class Frete {

    //@id expecifica para o spring que essa entidade é o Id, @generatedValue cria uma estrategia de gerar id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false) indica que a coluna dataServico nao pode ser vazia, LocalDate pega a hora e local e salva no db
    @Column(nullable = false)
    private LocalDate dataServico;

    //mudei de string para enum para que nao tenha como ser adicionado coisas aleatorias, como [e um projeto para mim acredito que seja melhor assim, e o @Enumerated(EnumType,STRING) é para que no banco seja salvo o texto e nao os numeros de sequencia do enum
    @ElementCollection // esta anotacao pede para o JPA criar uma tabela auxiliar no db
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "tb_frete_turnos")
    private List<Turno> turnos;

    //@Column(nullable = false) indica que a coluna dataServico nao pode ser vazia
    @Column(nullable = false)
    private BigDecimal faturamento;

    //Não adicionei @Column pois o gasto no dia pode ser nulo
    private BigDecimal gasto;

    private boolean isDobra;

    private boolean sdd;

    public Frete(FreteRequestDTO novoFrete) {
        this.dataServico = novoFrete.dataServico();
        this.turnos = novoFrete.turnos();
        this.faturamento = novoFrete.faturamento();
        this.gasto = novoFrete.gasto();
        this.isDobra = novoFrete.turnos() != null && novoFrete.turnos().size() > 1; // em vez de eu utilizar if na service, esta linha sozinha esta fazendo a verificação de se tem mais de um item na lista e isso faz com que o isDobra seja false ou true
        this.sdd = novoFrete.sdd();
    }






}
