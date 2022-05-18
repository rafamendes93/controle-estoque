package br.com.rafael.controleestoque.utils;

import br.com.rafael.controleestoque.entities.movimentoestoque.MovimentoEstoqueProjection;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentoEstoqueProjectionImpl implements MovimentoEstoqueProjection {

    private BigDecimal lucro;
    private Long totalEntrada;
    private Long totalSaida;
}
