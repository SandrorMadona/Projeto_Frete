// ... importações ...
package com.faturamentofrete.frete.controller;

import com.faturamentofrete.frete.freteDTO.FreteRequestDTO;
import com.faturamentofrete.frete.freteDTO.FreteResponseDTO;
import com.faturamentofrete.frete.service.FreteService;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fretes")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class FreteController {

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


}