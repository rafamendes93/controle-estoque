package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import br.com.rafael.controleestoque.entities.tipoproduto.TipoProdutoDto;
import org.springframework.stereotype.Component;

@Component
public class TipoProdutoMapper implements Mapper<TipoProduto, TipoProdutoDto> {
    @Override
    public TipoProduto toEntity(TipoProdutoDto tipoProdutoDto) {
        return TipoProduto.builder()
                .id(tipoProdutoDto.getId())
                .descricao(tipoProdutoDto.getDescricao())
                .build();
    }

    @Override
    public TipoProdutoDto toDto(TipoProduto tipoProduto) {
        return TipoProdutoDto.builder()
                .id(tipoProduto.getId())
                .descricao(tipoProduto.getDescricao())
                .build();
    }
}
