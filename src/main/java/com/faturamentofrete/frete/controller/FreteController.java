// ... importações ...
package com.faturamentofrete.frete.controller;

import com.faturamentofrete.frete.entity.CustoFixo;
import com.faturamentofrete.frete.entity.Frete;
import com.faturamentofrete.frete.freteDTO.FreteRequestDTO;
import com.faturamentofrete.frete.freteDTO.FreteResponseDTO;
import com.faturamentofrete.frete.service.FreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fretes")
@CrossOrigin(origins = "*")
public class FreteController {

    @Autowired
    private FreteService freteService;

    @GetMapping
    public List<FreteResponseDTO> listarTodos() {
        return freteService.listarTodos(); // Alterado para chamar o Service
    }

    @PostMapping
    public FreteResponseDTO salvar(@RequestBody FreteRequestDTO novoFrete) {
        return freteService.salvarFrete(novoFrete);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrete(@PathVariable Long id) {
        freteService.deletarFrete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/fixo")
    private List<CustoFixo> listarCustos(){
        return freteService.listarCustos();
    }

    @PostMapping("/fixo")
    public ResponseEntity<CustoFixo> salvarCusto(@RequestBody CustoFixo novoCusto){
        try {
            CustoFixo custoSalvo = freteService.salvarCustoFixo(novoCusto);
            return  ResponseEntity.ok(custoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}