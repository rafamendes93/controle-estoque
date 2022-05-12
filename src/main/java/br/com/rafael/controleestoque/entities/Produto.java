package br.com.rafael.controleestoque.entities;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String descricao;

    @NotNull
    @ManyToOne
    private TipoProduto tipo;

    @NotNull
    private BigDecimal valorCusto;

}
