package br.com.rafael.controleestoque.entities.movimentoestoque;

import java.math.BigDecimal;

public interface MovimentoEstoqueProjection {
    BigDecimal getLucro();

    Long getTotalEntrada();

    Long getTotalSaida();
}
