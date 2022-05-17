package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.entities.produto.ProdutoDto;
import br.com.rafael.controleestoque.mappers.ProdutoMapper;
import br.com.rafael.controleestoque.services.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController extends BaseController<ProdutoDto, ProdutoService, ProdutoMapper, Integer> {

    @GetMapping("/tipo/{tipoId}")
    public ResponseEntity<Page<ProdutoDto>> findByTipo(@PathVariable Integer tipoId, Pageable pageable) {
        Page<ProdutoDto> produtoDtoPage =
                service.findByTipo(tipoId, pageable)
                        .map(mapper::toDto);
        return ResponseEntity.ok(produtoDtoPage);
    }

}
