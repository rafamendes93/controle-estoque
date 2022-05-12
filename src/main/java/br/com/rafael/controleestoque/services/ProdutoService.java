package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.Produto;
import br.com.rafael.controleestoque.entities.dtos.ProdutoDto;
import br.com.rafael.controleestoque.mappers.ProdutoMapper;
import br.com.rafael.controleestoque.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    private final ProdutoRepository repo;
    private final ProdutoMapper mapper;

    @Autowired
    public ProdutoService(ProdutoRepository repo,
                          ProdutoMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    private Produto findById(Integer id) throws Exception {
        return repo.findById(id)
                .orElseThrow(() -> new Exception("produto.notfound"));
    }

    public ProdutoDto find(Integer id) throws Exception {
        return mapper.toDto(findById(id));
    }

    public void deleteById(Integer id) {
        repo.deleteById(id);
    }

    public ProdutoDto insert(ProdutoDto produtoDto) {
        Produto newProduto = repo.save(mapper.toEntity(produtoDto));
        return mapper.toDto(newProduto);
    }

    public ProdutoDto update(Integer id, ProdutoDto produtoDto) throws Exception {
        Produto produto = findById(id);
        produtoDto.setId(produto.getId());
        return insert(produtoDto);
    }
}
