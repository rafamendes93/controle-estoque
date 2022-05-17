package br.com.rafael.controleestoque.controller;

import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.mappers.Mapper;
import br.com.rafael.controleestoque.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class BaseController<D, S extends IService, M extends Mapper, I> {

    @Autowired
    protected S service;

    @Autowired
    protected M mapper;

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable I id) {
        D produtoDto = (D) mapper.toDto(service.findById(id));
        return ResponseEntity.ok(produtoDto);
    }

    @PostMapping
    public ResponseEntity<D> insert(@Valid @RequestBody D dto) {
        Object novaEntidade = service.insert(mapper.toEntity(dto));
        return ResponseEntity.ok((D) mapper.toDto(novaEntidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteById(@PathVariable I id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<D> update(@PathVariable I id,
                                    @Valid @RequestBody D dto) {
        Object produto = service.update(id, mapper.toEntity(dto));
        return ResponseEntity.ok((D) mapper.toDto(produto));
    }

}
