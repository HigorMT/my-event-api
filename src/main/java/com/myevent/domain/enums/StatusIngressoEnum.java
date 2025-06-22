package com.myevent.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum StatusIngressoEnum {

    AGUARDANDO_PAGAMENTO(1, "Aguardando pagamento"),
    EM_ESPERA(2, "Em Espera"),
    CANCELADO(3, "Cancelado"),
    EXPIRADO(4, "Expirado"),
    VALIDO(5, "Valido");


    private final Integer valor;
    private final String descricao;

    public static StatusIngressoEnum porValor(int valor) {
        return Stream.of(StatusIngressoEnum.values())
                .filter(t -> t.getValor() == valor)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor StatusIngressoEnum invalido: " + valor));
    }

}
