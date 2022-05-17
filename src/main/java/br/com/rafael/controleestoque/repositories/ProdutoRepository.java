package br.com.rafael.controleestoque.repositories;

import br.com.rafael.controleestoque.entities.produto.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {

    Page<Produto> findByTipoId(Integer tipoId, Pageable pageable);

}
