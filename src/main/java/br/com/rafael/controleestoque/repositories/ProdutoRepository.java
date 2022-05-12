package br.com.rafael.controleestoque.repositories;

import br.com.rafael.controleestoque.entities.Produto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Integer> {
}
