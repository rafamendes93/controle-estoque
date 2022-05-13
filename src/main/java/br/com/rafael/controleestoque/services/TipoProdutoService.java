package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.TipoProduto;
import br.com.rafael.controleestoque.exceptions.NotFoundException;
import br.com.rafael.controleestoque.messages.MessagesHandler;
import br.com.rafael.controleestoque.repositories.TipoProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProdutoService {

    private final TipoProdutoRepository repo;
    private final MessagesHandler messagesHandler;

    @Autowired
    public TipoProdutoService(TipoProdutoRepository repo,
                              MessagesHandler messagesHandler) {
        this.repo = repo;
        this.messagesHandler = messagesHandler;
    }

    public TipoProduto findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(messagesHandler.getMessage("tipo.notfound", id)));
    }
}
