package br.com.rafael.controleestoque.entities.tipoproduto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoProdutoDto {

    private Integer id;

    @NotNull(message = "${tipo_produto.descricao.isnull}")
    private String descricao;
}
