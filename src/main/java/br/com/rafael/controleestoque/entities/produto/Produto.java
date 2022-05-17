package br.com.rafael.controleestoque.entities.produto;

import br.com.rafael.controleestoque.entities.tipoproduto.TipoProduto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produto implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_produtos"
    )
    @SequenceGenerator(
            name = "sequence_id_produtos",
            sequenceName = "sequence_produto",
            allocationSize = 1
    )
    private Integer id;

    @NotNull
    private String descricao;

    @NotNull
    @ManyToOne
    private TipoProduto tipo;

    @NotNull
    private BigDecimal valorCusto;

}
