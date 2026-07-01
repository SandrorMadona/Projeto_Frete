package com.faturamentofrete.frete.controller;


import com.faturamentofrete.frete.entity.Combustivel;
import com.faturamentofrete.frete.entity.CustoFixo;
import com.faturamentofrete.frete.service.FreteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combustivel")
@AllArgsConstructor
public class CombustivelController {

    private FreteService freteService;

    @GetMapping()
    public List<Combustivel> listaCombustivel(){ return freteService.listaCombustivel(); }

    /* * O ResponseEntity é como se fosse o "envelope do correio" do protocolo HTTP.
     * Em vez de devolver apenas o dado puro (o objeto Combustivel), ele permite que
     * o Java envie para o Front-end uma resposta completa contendo:
     * 1. O Corpo (Body): Os dados salvos.
     * 2. O Cabeçalho (Headers): Informações técnicas da requisição.
     * 3. O Status Code: O mais importante! Ele diz para o navegador se deu certo (200 OK, 201 Created)
     * ou se deu erro (400 Bad Request, 404 Not Found, 500 Internal Error).
     */
    @PostMapping()
    public ResponseEntity<Combustivel> salvaCombustivel (@RequestBody Combustivel novoCombustivel){
        try {
            // Tenta processar a regra de negócio e salvar no banco
            Combustivel combustivelSalvo = freteService.salvaCombustivel(novoCombustivel);

            // Se deu tudo certo, cria o envelope marcando com o carimbo verde "200 OK"
            // e coloca o objeto salvo (já com ID) dentro do envelope.
            return ResponseEntity.ok(combustivelSalvo);

        } catch (IllegalArgumentException e) {
            // Se algo falhar na validação, cria um envelope com carimbo vermelho "400 Bad Request"
            // .build() fecha o envelope vazio, enviando apenas o aviso do erro para o Front-end.
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCombustivel(@PathVariable Long id ){
        freteService.deletaCombustivel(id);
        return ResponseEntity.noContent().build();
    }
}
