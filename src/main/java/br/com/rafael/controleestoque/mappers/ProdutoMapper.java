package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.Produto;
import br.com.rafael.controleestoque.entities.TipoProduto;
import br.com.rafael.controleestoque.entities.dtos.ProdutoDto;
import br.com.rafael.controleestoque.services.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoMapper {

    private final TipoProdutoService tipoProdutoService;

    @Autowired
    public ProdutoMapper(TipoProdutoService tipoProdutoService) {
        this.tipoProdutoService = tipoProdutoService;
    }


    public Produto toEntity(ProdutoDto dto) throws Exception {
        return Produto.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .tipo(getTipo(dto.getTipo()))
                .valorCusto(dto.getValorCusto())
                .build();
    }

    public ProdutoDto toDto(Produto produto) {
        return ProdutoDto.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .tipo(produto.getTipo().getId())
                .valorCusto(produto.getValorCusto())
                .build();
    }

    private TipoProduto getTipo(Integer id) throws Exception {
        return tipoProdutoService.findById(id);
    }
}
