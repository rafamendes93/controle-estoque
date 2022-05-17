package br.com.rafael.controleestoque.entities.tipoproduto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoProduto implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_id_tipo_produto"
    )
    @SequenceGenerator(
            name = "sequence_id_tipo_produto",
            sequenceName = "sequence_tipo_produto",
            allocationSize = 1
    )
    private Integer id;

    private String descricao;
}
