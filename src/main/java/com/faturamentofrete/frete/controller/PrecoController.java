package com.faturamentofrete.frete.controller;

import com.faturamentofrete.frete.entity.TabelaPreco;
import com.faturamentofrete.frete.service.FreteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/precos")
@AllArgsConstructor
public class PrecoController {

    private FreteService freteService;

    @GetMapping()
    private Optional<TabelaPreco> valorPreco() {
        return freteService.valorPreco();
    }

    @PostMapping()
    public ResponseEntity<TabelaPreco> salvarPreco(@RequestBody TabelaPreco novoPreco) {
        try {
            TabelaPreco precoSalvo = freteService.salvarPreco(novoPreco);
            return ResponseEntity.ok(precoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
