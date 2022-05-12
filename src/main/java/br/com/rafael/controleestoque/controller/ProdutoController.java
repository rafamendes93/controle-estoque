package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.entities.Produto;
import br.com.rafael.controleestoque.entities.dtos.ProdutoDto;
import br.com.rafael.controleestoque.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/produto")
public class ProdutoController {

    private final ProdutoService service;

    @Autowired
    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Integer id) throws Exception {
        ProdutoDto produtoDto = service.find(id);
        return ResponseEntity.ok(produtoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteById(@PathVariable Integer id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ProdutoDto> insert(@RequestBody ProdutoDto dto) {
        ProdutoDto novoProduto = service.insert(dto);
        return ResponseEntity.ok(novoProduto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Integer id,
                                             @RequestBody ProdutoDto dto) throws Exception {
        ProdutoDto produtoDto = service.update(id, dto);
        return ResponseEntity.ok(produtoDto);
    }

}
