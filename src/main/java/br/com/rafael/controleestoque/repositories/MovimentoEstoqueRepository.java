package br.com.rafael.controleestoque.repositories;

import br.com.rafael.controleestoque.entities.MovimentoEstoque;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoEstoqueRepository extends CrudRepository<MovimentoEstoque, Integer> {
}
