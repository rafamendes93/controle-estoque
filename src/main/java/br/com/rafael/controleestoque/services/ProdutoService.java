package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.exceptions.NaoEncontradoException;
import br.com.rafael.controleestoque.exceptions.ProdutoComMovimentoException;
import br.com.rafael.controleestoque.repositories.ProdutoRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService extends BaseService<ProdutoRepository> implements IService<Produto, Integer> {

    public Produto findById(Integer id) {
        return repo.findById(id)
                .orElseThrow(() -> new NaoEncontradoException(messagesHandler.getMessage("produto.notfound", id)));
    }

    public Page<Produto> findByTipo(Integer tipoId, Pageable pageable) {
        return repo.findByTipoId(tipoId, pageable);
    }

    public void deleteById(Integer id) {
        try {
            repo.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ProdutoComMovimentoException(messagesHandler.getMessage("produto.com_movimento", id));
        }
    }

    public Produto insert(Produto produto) {
        produto.setId(null);
        return repo.save(produto);
    }

    public Produto update(Integer id, Produto produto) {
        produto.setId(findById(id).getId());
        return repo.save(produto);
    }
}
