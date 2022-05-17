package br.com.rafael.controleestoque.entities.movimentoestoque;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentoEstoqueDto implements Serializable {

    private Integer id;

    @NotNull(message = "${movimento.tipo_movimento.isnull}")
    private TipoMovimento tipoMovimento;

    private BigDecimal valorVenda;

    private LocalDate dataVenda;

    @NotNull(message = "${movimento.quantidade.isnull}")
    private Integer quantidade;

    @NotNull(message = "${movimento.produto.isnull}")
    private Integer produtoId;

}
