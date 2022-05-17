package br.com.rafael.controleestoque.repositories;

import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoque;
import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoqueProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoEstoqueRepository extends CrudRepository<MovimentoEstoque, Integer> {


    @Query("SELECT sum((mov.valorVenda - p.valorCusto)) as lucro, " +
            "       (SELECT sum(quantidade) from MovimentoEstoque WHERE tipoMovimento = 'E' and produto.id = :produtoId) as totalEntrada, " +
            "       (SELECT sum(quantidade) from MovimentoEstoque WHERE tipoMovimento = 'S' and produto.id = :produtoId) as totalSaida " +
            " FROM MovimentoEstoque mov " +
            "         JOIN Produto p on p.id = mov.produto.id " +
            " WHERE mov.tipoMovimento = 'S' " +
            " AND mov.produto.id = :produtoId")
    MovimentoEstoqueProjection findTotalLucroByProduto(Integer produtoId);
}