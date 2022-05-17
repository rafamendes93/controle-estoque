package br.com.rafael.controleestoque.entities.movimentoestoque;

import br.com.rafael.controleestoque.entities.produto.Produto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentoEstoque implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_movimento_estoque"
    )
    @SequenceGenerator(
            name = "sequence_id_movimento_estoque",
            sequenceName = "sequence_movimento_estoque",
            allocationSize = 1
    )
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;

    private BigDecimal valorVenda;

    private LocalDate dataVenda;

    private Integer quantidade;

    @ManyToOne
    private Produto produto;

}
