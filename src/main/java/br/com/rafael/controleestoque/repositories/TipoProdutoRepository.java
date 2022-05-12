package br.com.rafael.controleestoque.repositories;

import br.com.rafael.controleestoque.entities.TipoProduto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoProdutoRepository extends CrudRepository<TipoProduto, Integer> {
}
