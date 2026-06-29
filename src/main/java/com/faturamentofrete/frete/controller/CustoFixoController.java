package com.faturamentofrete.frete.controller;

import com.faturamentofrete.frete.entity.CustoFixo;
import com.faturamentofrete.frete.service.FreteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fixos")
@AllArgsConstructor
public class CustoFixoController {

    private FreteService freteService;

    @GetMapping()
    private List<CustoFixo> listarCustos(){
        return freteService.listarCustos();
    }

    @PostMapping()
    public ResponseEntity<CustoFixo> salvarCusto(@RequestBody CustoFixo novoCusto){
        try {
            CustoFixo custoSalvo = freteService.salvarCustoFixo(novoCusto);
            return  ResponseEntity.ok(custoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
