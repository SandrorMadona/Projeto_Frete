package com.faturamentofrete.frete.service;

import com.faturamentofrete.frete.entity.CustoFixo;
import com.faturamentofrete.frete.entity.Frete;
import com.faturamentofrete.frete.freteDTO.FreteRequestDTO;
import com.faturamentofrete.frete.freteDTO.FreteResponseDTO;
import com.faturamentofrete.frete.repository.CustoFixoRepository;
import com.faturamentofrete.frete.repository.FreteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreteService {

    /* @RequiredArdsConstructor faz a injeção da dependencia e injeta na variavel nas classes que possuem o "final"*/
    private final FreteRepository freteRepository;

    private final CustoFixoRepository custoFixoRepository;

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

        if (frete.getFaturamento() == null || frete.getFaturamento().doubleValue() <= 0) {
            throw new IllegalArgumentException("O faturamento não pode ser zero ou negativo");
        }

        if (frete.getTurnos() != null && frete.getTurnos().contains(",")) {
            frete.setDobra(true);
        } else {
            frete.setDobra(false);
        }

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

}