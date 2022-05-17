package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import br.com.rafael.controleestoque.exceptions.NaoEncontradoException;
import br.com.rafael.controleestoque.repositories.TipoProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoProdutoService extends BaseService<TipoProdutoRepository> {

    public TipoProduto findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(messagesHandler.getMessage("tipo.notfound", id)));
    }
}
