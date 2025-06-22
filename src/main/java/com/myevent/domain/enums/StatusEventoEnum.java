package com.myevent.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusEventoEnum {

    EM_ANDAMENTO(1, "Em Aindamento"),
    NAO_INICIADO(2, "evento não iniciado"),
    CANCELADO(3, "Cancelado"),
    FINALIZADO(4, "Finalizado");

    private final Integer valor;
    private final String descricao;

    public static StatusEventoEnum porValor(int valor) {
        return Stream.of(StatusEventoEnum.values())
                .filter(t -> t.getValor() == valor)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor StatusEventoEnum invalido: " + valor));
    }

}
