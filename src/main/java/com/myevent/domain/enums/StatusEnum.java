package com.myevent.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    
    INATIVO(0, "Inativo"),
    ATIVO(1, "Ativo");

    private final Integer valor;
    private final String descricao;

    public static StatusEnum porValor(int valor) {
        return Stream.of(StatusEnum.values())
                .filter(t -> t.getValor() == valor)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor Enum invalido: " + valor));
    }

    public static StatusEnum porDescricao(String descricao) {
        return Stream.of(StatusEnum.values())
                .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Descrição Enum invalido: " + descricao));
    }

}
