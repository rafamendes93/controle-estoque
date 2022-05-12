package br.com.rafael.controleestoque.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMovimento {
    E("Entrada"),
    S("Saida");

    private final String descricao;
}
