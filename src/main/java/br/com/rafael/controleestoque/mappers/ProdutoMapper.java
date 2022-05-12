package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.Produto;
import br.com.rafael.controleestoque.entities.dtos.ProdutoDto;
import org.springframework.stereotype.Service;

@Service
public class ProdutoMapper {

    public Produto toEntity(ProdutoDto dto) {
        return Produto.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .tipo(dto.getTipo())
                .valorCusto(dto.getValorCusto())
                .build();
    }

    public ProdutoDto toDto(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .tipo(produto.getTipo())
                .build();
    }
}
