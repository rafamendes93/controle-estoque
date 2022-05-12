package br.com.rafael.controleestoque.entities.dtos;

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

    @NotNull
    private String descricao;

    @NotNull
    private Integer tipo;

    @NotNull
    private BigDecimal valorCusto;

    private Integer quantidade;
}
