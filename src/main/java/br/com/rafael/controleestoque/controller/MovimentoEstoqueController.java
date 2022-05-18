package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.controller.base.BaseController;
import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoque;
import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoqueDto;
import br.com.rafael.controleestoque.mappers.MovimentoEstoqueMapper;
import br.com.rafael.controleestoque.services.MovimentoEstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/movimento")
public class MovimentoEstoqueController extends BaseController<MovimentoEstoqueService, MovimentoEstoqueMapper> {

    @PostMapping
    public ResponseEntity<MovimentoEstoqueDto> criarMovimento(@Valid @RequestBody MovimentoEstoqueDto dto) {
        MovimentoEstoque movimentoCriado = service.criarMovimento(mapper.toEntity(dto));
        return ResponseEntity.ok(mapper.toDto(movimentoCriado));
    }

}
