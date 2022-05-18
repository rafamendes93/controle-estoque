package br.com.rafael.controleestoque.utils;

import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.entities.produto.ProdutoDto;
import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.util.List;

public class ProdutoFactory {

    public static Produto build() {
        return Produto.builder()
                .id(1)
                .descricao("Produto teste")
                .valorCusto(BigDecimal.valueOf(1500.00))
                .tipo(TipoProduto.builder()
                        .id(1)
                        .descricao("Tipo Teste")
                        .build())
                .build();
    }

    public static Produto build(Integer id, String descricao) {
        return Produto.builder()
                .id(id)
                .descricao(descricao)
                .valorCusto(BigDecimal.valueOf(1500.00))
                .tipo(TipoProduto.builder()
                        .id(1)
                        .descricao("Tipo Teste")
                        .build())
                .build();
    }

    public static ProdutoDto buildDto() {
        return ProdutoDto.builder()
                .id(1)
                .descricao("Produto teste")
                .tipo(TipoProduto.builder()
                        .id(1)
                        .descricao("Tipo Teste")
                        .build())
                .valorCusto(BigDecimal.valueOf(1500.00))
                .lucro(BigDecimal.valueOf(800.00))
                .quantidadeEntrada(10L)
                .quantidadeEstoque(5L)
                .quantidadeVenda(5L)
                .build();
    }

    public static Page<Produto> buildPaged() {
        return new PageImpl<>(List.of(build(1, "Produto teste 1"), build(2, "Produto teste 2")));
    }
}
