package br.com.rafael.controleestoque.controller.base;

import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.mappers.Mapper;
import br.com.rafael.controleestoque.services.base.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public abstract class CrudController<D, S extends CrudService, M extends Mapper, I> extends BaseController<S, M> {

    @GetMapping("/{id}")
    public ResponseEntity<D> findById(@PathVariable I id) throws Throwable {
        D produtoDto = (D) mapper.toDto(service.findById(id));
        return ResponseEntity.ok(produtoDto);
    }

    @PostMapping
    public ResponseEntity<D> insert(@Valid @RequestBody D dto) throws Throwable {
        Object novaEntidade = service.insert(mapper.toEntity(dto));
        return ResponseEntity.ok((D) mapper.toDto(novaEntidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Produto> deleteById(@PathVariable I id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
