package com.faturamentofrete.frete.service;

import com.faturamentofrete.frete.entity.CustoFixo;
import com.faturamentofrete.frete.entity.Frete;
import com.faturamentofrete.frete.entity.TabelaPreco;
import com.faturamentofrete.frete.freteDTO.FreteRequestDTO;
import com.faturamentofrete.frete.freteDTO.FreteResponseDTO;
import com.faturamentofrete.frete.repository.CustoFixoRepository;
import com.faturamentofrete.frete.repository.FreteRepository;
import com.faturamentofrete.frete.repository.TabelaPrecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreteService {

    /* @RequiredArdsConstructor faz a injeção da dependencia e injeta na variavel nas classes que possuem o "final"*/
    private final FreteRepository freteRepository;

    private final CustoFixoRepository custoFixoRepository;

    private final TabelaPrecoRepository tabelaPrecoRepository;

    // Novo metodo que criamos para o Controller usar
    public List<FreteResponseDTO> listarTodos() {

        Sort sort = Sort.by("dataServico").descending()
                .and(Sort.by("id").ascending());
        return freteRepository.findAll(sort)
                .stream()
                .map(FreteResponseDTO::new)
                .toList();
    }


    public FreteResponseDTO salvarFrete(FreteRequestDTO novoFrete){
        Frete frete = new Frete(novoFrete);

        Frete salvo =freteRepository.save(frete);

        return new FreteResponseDTO(salvo);
    }


    public void deletarFrete(Long id){
        if (!freteRepository.existsById(id)){
            throw new RuntimeException("Frete não encontrado com id: " + id);
        }
        freteRepository.deleteById(id);
    }

    public List<CustoFixo> listarCustos(){
        return custoFixoRepository.findAll();
    }

    public CustoFixo salvarCustoFixo(CustoFixo novoCustoFixo) {
        return custoFixoRepository.save(novoCustoFixo);
    }

    public TabelaPreco salvarPreco(TabelaPreco novoPreco){ return tabelaPrecoRepository.save(novoPreco);}

    public List<TabelaPreco> listarPrecos() { return tabelaPrecoRepository.findAll();}

}