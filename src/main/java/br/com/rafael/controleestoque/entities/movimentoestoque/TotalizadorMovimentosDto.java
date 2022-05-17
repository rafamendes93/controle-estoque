package br.com.rafael.controleestoque.entities.movimentoestoque;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TotalizadorMovimentosDto implements Serializable {

    private Long quantidadeEstoque;

    private Long quantidadeSaida;

    private Long quantidadeEntrada;

    private BigDecimal lucro;
}
