package br.com.rafael.controleestoque.entities;

import br.com.rafael.controleestoque.entities.enums.TipoMovimento;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TipoMovimento tipoMovimento;

    private BigDecimal valorVenda;

    private LocalDate dataVenda;

    private Integer quantidade;

    @ManyToOne
    private Produto produto;

}
