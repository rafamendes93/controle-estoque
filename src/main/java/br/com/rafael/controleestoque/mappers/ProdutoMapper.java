package br.com.rafael.controleestoque.mappers;

import br.com.rafael.controleestoque.entities.movimentoestoque.TotalizadorMovimentosDto;
import br.com.rafael.controleestoque.entities.produto.Produto;
import br.com.rafael.controleestoque.entities.produto.ProdutoDto;
import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import br.com.rafael.controleestoque.services.MovimentoEstoqueService;
import br.com.rafael.controleestoque.services.TipoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoMapper implements Mapper<Produto, ProdutoDto> {

    private final TipoProdutoService tipoProdutoService;
    private final MovimentoEstoqueService movimentoEstoqueService;

    @Autowired
    public ProdutoMapper(TipoProdutoService tipoProdutoService,
                         MovimentoEstoqueService movimentoEstoqueService) {
        this.tipoProdutoService = tipoProdutoService;
        this.movimentoEstoqueService = movimentoEstoqueService;
    }

    public Produto toEntity(ProdutoDto dto) {
        return Produto.builder()
                .id(dto.getId())
                .descricao(dto.getDescricao())
                .tipo(getTipo(dto.getTipo().getId()))
                .valorCusto(dto.getValorCusto())
                .build();
    }

    public ProdutoDto toDto(Produto produto) {
        ProdutoDto produtoDto = ProdutoDto.builder()
                .id(produto.getId())
                .descricao(produto.getDescricao())
                .tipo(produto.getTipo())
                .valorCusto(produto.getValorCusto())
                .build();

        TotalizadorMovimentosDto movimento = getMovimento(produto.getId());
        produtoDto.setQuantidadeEstoque(movimento.getQuantidadeEstoque());
        produtoDto.setQuantidadeVenda(movimento.getQuantidadeSaida());
        produtoDto.setLucro(movimento.getLucro());
        produtoDto.setQuantidadeEntrada(movimento.getQuantidadeEntrada());

        return produtoDto;
    }

    private TotalizadorMovimentosDto getMovimento(Integer produtoId) {
        return movimentoEstoqueService.getQuantidadeByProduto(produtoId);
    }

    private TipoProduto getTipo(Integer id) {
        return tipoProdutoService.findById(id);
    }
}
