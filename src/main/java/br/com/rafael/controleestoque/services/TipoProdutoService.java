package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.TipoProduto;
import br.com.rafael.controleestoque.repositories.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProdutoService {

    private final TipoProdutoRepository repo;

    @Autowired
    public TipoProdutoService(TipoProdutoRepository repo) {
        this.repo = repo;
    }

    public TipoProduto findById(Integer id) throws Exception {
        return repo.findById(id)
                .orElseThrow(() -> new Exception("produto.notfound"));
    }
}
