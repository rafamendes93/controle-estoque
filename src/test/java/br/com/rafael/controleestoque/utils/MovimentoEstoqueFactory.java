package br.com.rafael.controleestoque.utils;

import br.com.rafael.controleestoque.entities.movimentoestoque.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MovimentoEstoqueFactory {

    public static MovimentoEstoque build() {
        return MovimentoEstoque.builder()
                .id(1)
                .produto(ProdutoFactory.build())
                .valorVenda(BigDecimal.valueOf(1500.00))
                .dataVenda(LocalDate.now())
                .tipoMovimento(TipoMovimento.E)
                .quantidade(10)
                .build();
    }

    public static MovimentoEstoque buildEntrada() {
        return build();
    }

    public static MovimentoEstoque buildSaida() {
        MovimentoEstoque build = build();
        build.setTipoMovimento(TipoMovimento.S);
        build.setQuantidade(1);
        return build;
    }

    public static MovimentoEstoqueDto buildDto() {
        return MovimentoEstoqueDto.builder()
                .id(1)
                .produtoId(1)
                .valorVenda(BigDecimal.valueOf(1500.00))
                .dataVenda(LocalDate.now())
                .tipoMovimento(TipoMovimento.E)
                .quantidade(10)
                .build();
    }

    public static TotalizadorMovimentosDto buildTotalizador() {
        return TotalizadorMovimentosDto.builder()
                .lucro(BigDecimal.valueOf(1500.00))
                .quantidadeEntrada(10L)
                .quantidadeSaida(5L)
                .quantidadeEstoque(5L)
                .build();
    }
}
