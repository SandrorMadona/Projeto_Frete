package com.faturamentofrete.frete.service;

import com.faturamentofrete.frete.entity.CustoFixo;
import com.faturamentofrete.frete.entity.Frete;
import com.faturamentofrete.frete.entity.TabelaPreco;
import com.faturamentofrete.frete.entity.TurnoRealizado;
import com.faturamentofrete.frete.freteDTO.FreteRequestDTO;
import com.faturamentofrete.frete.freteDTO.FreteResponseDTO;
import com.faturamentofrete.frete.repository.CustoFixoRepository;
import com.faturamentofrete.frete.repository.FreteRepository;
import com.faturamentofrete.frete.repository.TabelaPrecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service // Indica ao Spring que esta classe é um componente de Serviço, onde moram as regras de negócio e cálculos do sistema.
@RequiredArgsConstructor // Cria automaticamente um construtor contendo todos os atributos declarados como 'final', realizando a Injeção de Dependências.
public class FreteService {

    // Atributos definidos como 'final' para garantir imutabilidade e permitir a injeção segura de dependências dos Repositories.
    private final FreteRepository freteRepository;
    private final CustoFixoRepository custoFixoRepository;
    private final TabelaPrecoRepository tabelaPrecoRepository;

    /**
     * Recupera todos os fretes salvos no sistema, aplicando uma ordenação customizada
     * e transformando as Entidades em DTOs de resposta para o front-end.
     */
    public List<FreteResponseDTO> listarTodos() {
        // Define o critério de ordenação: primeiro pela data do serviço (mais recente para a mais antiga),
        // e em caso de datas iguais, ordena pelo ID de forma crescente.
        Sort sort = Sort.by("dataServico").descending()
                .and(Sort.by("id").ascending());

        // Busca os dados ordenados, abre um fluxo de dados (stream), converte cada elemento para o DTO
        // utilizando o construtor de referência (FreteResponseDTO::new) e agrupa o resultado em uma lista.
        return freteRepository.findAll(sort)
                .stream()
                .map(FreteResponseDTO::new)
                .toList();
    }

    /**
     * O motor de cálculo principal do sistema. Recebe os fatos brutos do front-end,
     * processa a lógica de precificação com base na tabela ativa e persiste os dados de forma segura.
     */
    public FreteResponseDTO salvarFrete(FreteRequestDTO novoFrete){

        // Instancia uma nova entidade Frete utilizando o construtor que mapeia os dados do DTO recebido.
        Frete frete = new Frete(novoFrete);

        // Busca as configurações de valores vigentes no banco (Tabela única de ID 1).
        // Lança uma exceção caso o administrador do sistema ainda não tenha configurado os preços.
        TabelaPreco tabelaAtual = tabelaPrecoRepository.findById(1L).orElseThrow();

        // Inicializa a variável acumuladora do cálculo com o valor zero absoluto do BigDecimal.
        BigDecimal faturamentoTotal = BigDecimal.ZERO;

        // Laço de repetição que analisa individualmente cada turno operado informado na requisição.
        for (TurnoRealizado turnoAtual : novoFrete.turnoRealizado()){

            // Estrutura de controle que lê a constante do Enum (AM, PM, SD) e soma o valor base correto.
            // Nota de arquitetura: Como o BigDecimal é imutável, o resultado da soma sempre deve ser reatribuído à variável.
            switch(turnoAtual.getTurno()){
                case AM -> faturamentoTotal = faturamentoTotal.add(tabelaAtual.getAm());
                case PM -> faturamentoTotal = faturamentoTotal.add(tabelaAtual.getPm());
                case SD -> faturamentoTotal = faturamentoTotal.add(tabelaAtual.getSd());
            }

            // Verifica se este percurso específico possui direito ao bônus de rota diferenciada (SDD).
            if (turnoAtual.isSdd()){
                // Caso verdadeiro, acumula o valor adicional configurado na tabela de preços.
                faturamentoTotal = faturamentoTotal.add(tabelaAtual.getSdd());
            }
        }

        // Injeta o valor final calculado com segurança pelo back-end no atributo de faturamento da entidade.
        frete.setFaturamento(faturamentoTotal);

        // Salva a entidade completa no banco de dados (gerando o ID e persistindo a tabela auxiliar de turnos).
        Frete salvo = freteRepository.save(frete);

        // Retorna o DTO estruturado de resposta contendo todos os dados finais gravados.
        return new FreteResponseDTO(salvo);
    }

    /**
     * Remove um registro de frete do histórico utilizando o ID. Inclui uma validação prévia
     * para evitar erros de integridade no banco de dados.
     */
    public void deletarFrete(Long id){
        // Verifica se o ID informado realmente existe no banco antes de tentar a exclusão.
        if (!freteRepository.existsById(id)){
            throw new RuntimeException("Frete não encontrado com id: " + id);
        }
        freteRepository.deleteById(id);
    }

    // ==========================================
    // SEÇÃO DE ADMINISTRAÇÃO: CUSTO FIXO
    // ==========================================

    /**
     * Salva ou atualiza as configurações de custos operacionais fixos.
     * Segue a estratégia de "Upsert" baseada no ID controlado pela interface.
     */
    public CustoFixo salvarCustoFixo(CustoFixo novoCustoFixo) {
        return custoFixoRepository.save(novoCustoFixo);
    }

    /**
     * Retorna a listagem de custos fixos cadastrados para análise financeira.
     */
    public List<CustoFixo> listarCustos(){
        return custoFixoRepository.findAll();
    }

    // ==========================================
    // SEÇÃO DE ADMINISTRAÇÃO: TABELA DE PREÇOS
    // ==========================================

    /**
     * Grava ou modifica os valores financeiros de referência para cada período do dia.
     * Como a entidade gerencia um registro fixo (ID 1), este metodo atua atualizando os valores existentes.
     */
    public TabelaPreco salvarPreco(TabelaPreco novoPreco){
        return tabelaPrecoRepository.save(novoPreco);
    }

    /**
     * Busca as configurações vigentes de valores das rotas.
     * Retorna um contêiner 'Optional' para proteger o sistema caso o banco de dados esteja zerado.
     */
    public Optional<TabelaPreco> valorPreco() {
        // Ajustado de findById() para findById(1L) para referenciar diretamente a linha única de preço.
        return tabelaPrecoRepository.findById(1L);
    }
}