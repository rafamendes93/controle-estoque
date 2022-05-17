package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoqueDto;
import br.com.rafael.controleestoque.services.MovimentoEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/movimento")
public class MovimentoEstoqueController {

    private final MovimentoEstoqueService service;

    @Autowired
    public MovimentoEstoqueController(MovimentoEstoqueService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MovimentoEstoqueDto> criarMovimento(@Valid @RequestBody MovimentoEstoqueDto movimentoDto) {
        MovimentoEstoqueDto movimentoCriado = service.criarMovimento(movimentoDto);
        return ResponseEntity.ok(movimentoCriado);
    }

}
