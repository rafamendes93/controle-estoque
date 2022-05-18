package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.repositories.ProdutoRepository;
import br.com.rafael.controleestoque.services.base.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends CrudService<Produto, ProdutoRepository, Integer> {

    public Page<Produto> findByTipo(Integer tipoId, Pageable pageable) {
        return repo.findByTipoId(tipoId, pageable);
    }
}
