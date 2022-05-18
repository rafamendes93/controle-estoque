package br.com.rafael.controleestoque.services;

import br.com.rafael.controleestoque.entities.movimentoestoque.*;
import br.com.rafael.controleestoque.exceptions.EstoqueInsuficienteException;
import br.com.rafael.controleestoque.repositories.MovimentoEstoqueRepository;
import br.com.rafael.controleestoque.services.base.BaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static java.util.Objects.isNull;

@Service
public class MovimentoEstoqueService extends BaseService<MovimentoEstoqueRepository> {

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

    public MovimentoEstoque criarMovimento(MovimentoEstoque movimento) {
        movimento.setId(null);
        return movimento.getTipoMovimento() == TipoMovimento.E ?
                criarMovimentoEntrada(movimento) :
                criarMovimentoSaida(movimento);
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

    private MovimentoEstoque criarMovimentoEntrada(MovimentoEstoque movimento) {
        return repo.save(movimento);
    }
}
