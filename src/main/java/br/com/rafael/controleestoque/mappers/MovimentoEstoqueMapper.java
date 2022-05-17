package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoque;
import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoqueDto;
import br.com.rafael.controleestoque.entities.produto.Produto;
import org.springframework.stereotype.Component;

@Component
public class MovimentoEstoqueMapper implements Mapper<MovimentoEstoque, MovimentoEstoqueDto> {

    public MovimentoEstoque toEntity(MovimentoEstoqueDto dto) {
        return MovimentoEstoque.builder()
                .id(dto.getId())
                .produto(Produto
                        .builder()
                        .id(dto.getProdutoId())
                        .build())
                .tipoMovimento(dto.getTipoMovimento())
                .quantidade(dto.getQuantidade())
                .dataVenda(dto.getDataVenda())
                .valorVenda(dto.getValorVenda())
                .build();
    }

    public MovimentoEstoqueDto toDto(MovimentoEstoque entity) {
        return MovimentoEstoqueDto.builder()
                .id(entity.getId())
                .produtoId(entity.getProduto().getId())
                .tipoMovimento(entity.getTipoMovimento())
                .quantidade(entity.getQuantidade())
                .dataVenda(entity.getDataVenda())
                .valorVenda(entity.getValorVenda())
                .build();
    }
}
