package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.Produto;
import br.com.rafael.controleestoque.entities.dtos.ProdutoDto;
import br.com.rafael.controleestoque.exceptions.NotFoundException;
import br.com.rafael.controleestoque.mappers.ProdutoMapper;
import br.com.rafael.controleestoque.messages.MessagesHandler;
import br.com.rafael.controleestoque.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;
    private final ProdutoMapper mapper;
    private final MessagesHandler messagesHandler;

    @Autowired
    public ProdutoService(ProdutoRepository repo,
                          ProdutoMapper mapper,
                          MessagesHandler messagesHandler) {
        this.repo = repo;
        this.mapper = mapper;
        this.messagesHandler = messagesHandler;
    }

    private Produto findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NotFoundException(messagesHandler.getMessage("produto.notfound", id)));
    }

    public ProdutoDto find(Integer id) {
        return mapper.toDto(findById(id));
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public ProdutoDto insert(ProdutoDto produtoDto) {
        produtoDto.setId(null);
        Produto newProduto = repo.save(mapper.toEntity(produtoDto));
        return mapper.toDto(newProduto);
    }

    public ProdutoDto update(Integer id, ProdutoDto produtoDto) {
        Produto produto = findById(id);
        produtoDto.setId(produto.getId());
        return insert(produtoDto);
    }
}
