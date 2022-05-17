package br.com.rafael.controleestoque.entities.produto;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDto implements Serializable {

    private Integer id;

    @NotNull(message = "${produto.descricao.notnull}")
    private String descricao;

    @NotNull(message = "${produto.tipo.notnull}")
    private TipoProduto tipo;

    @NotNull(message = "${produto.valor_custo.notnull}")
    private BigDecimal valorCusto;

    private Long quantidadeEstoque;

    private Long quantidadeVenda;

    private Long quantidadeEntrada;

    private BigDecimal lucro;
}
