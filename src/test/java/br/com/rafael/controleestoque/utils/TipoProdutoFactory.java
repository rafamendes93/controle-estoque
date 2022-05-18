package br.com.rafael.controleestoque.utils;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import br.com.rafael.controleestoque.entities.tipoproduto.TipoProdutoDto;

public class TipoProdutoFactory {

    public static TipoProduto build() {
        return build(1, "Tipo produto Teste");
    }

    public static TipoProduto build(Integer id, String descricao) {
        return TipoProduto.builder()
                .id(id)
                .descricao(descricao)
                .build();
    }


    public static TipoProdutoDto buildDto() {
        return TipoProdutoDto.builder()
                .id(1)
                .descricao("Tipo produto teste")
                .build();
    }
}
