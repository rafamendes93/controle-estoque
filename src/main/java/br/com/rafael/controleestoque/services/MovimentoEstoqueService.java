package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.movimentoestoque.*;
import br.com.rafael.controleestoque.exceptions.EstoqueInsuficienteException;
import br.com.rafael.controleestoque.mappers.MovimentoEstoqueMapper;
import br.com.rafael.controleestoque.repositories.MovimentoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Service
public class MovimentoEstoqueService extends BaseService<MovimentoEstoqueRepository> {

    @Autowired
    private MovimentoEstoqueMapper mapper;

    public TotalizadorMovimentosDto getQuantidadeByProduto(Integer produtoId) {
        MovimentoEstoqueProjection total = repo.findTotalLucroByProduto(produtoId);

        return TotalizadorMovimentosDto.builder()
                .quantidadeEstoque(calcularEstoque(total))
                .quantidadeSaida(isNull(total.getTotalSaida()) ? 0 : total.getTotalSaida())
                .lucro(isNull(total.getLucro()) ? BigDecimal.ZERO : total.getLucro())
                .quantidadeEntrada(isNull(total.getTotalEntrada()) ? 0L : total.getTotalEntrada())
                .build();
    }

    private Long calcularEstoque(MovimentoEstoqueProjection total) {
        return (isNull(total.getTotalEntrada()) ? 0 : total.getTotalEntrada()) -
                (isNull(total.getTotalSaida()) ? 0 : total.getTotalSaida());
    }

    public MovimentoEstoqueDto criarMovimento(MovimentoEstoqueDto movimentoDto) {
        movimentoDto.setId(null);
        MovimentoEstoque movimentoEstoque = mapper.toEntity(movimentoDto);
        movimentoEstoque = movimentoEstoque.getTipoMovimento() == TipoMovimento.E ?
                criarMovimentoEntrada(movimentoEstoque) :
                criarMovimentoSaida(movimentoEstoque);
        return mapper.toDto(movimentoEstoque);
    }

    private MovimentoEstoque criarMovimentoSaida(MovimentoEstoque movimento) {
        Assert.notNull(movimento.getDataVenda(), messagesHandler.getMessage("movimento.data_venda.isnull"));
        Assert.notNull(movimento.getValorVenda(), messagesHandler.getMessage("movimento.valor_venda.isnull"));
        TotalizadorMovimentosDto quantidadeByProduto = getQuantidadeByProduto(movimento.getProduto().getId());
        verificarEstoque(quantidadeByProduto.getQuantidadeEstoque(), movimento.getQuantidade());
        return repo.save(movimento);
    }

    private void verificarEstoque(Long estoque, Integer quantidadeMovimento) {
        if (estoque < quantidadeMovimento)
            throw new EstoqueInsuficienteException(messagesHandler.getMessage("movimento.estoque.insuficiente"));
    }

    public MovimentoEstoque criarMovimentoEntrada(MovimentoEstoque movimento) {
        return repo.save(movimento);
    }
}
