package com.myevent.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum PermissaoEnum {

    CAD_USUARIO(0, "Cadastrar  registros de Usuários."),
    ALT_USUARIO(1, "Alterar o registro de Usuários."),
    VIS_USUARIO(2, "Visualizar registros de Usuários."),
    SIT_USUARIO(3, "Atualizar situação dos registros de Usuários."),

    CAD_PERFIL(4, "Cadastrar  registros de Perfis."),
    ALT_PERFIL(5, "Alterar o registro de Perfis."),
    VIS_PERFIL(6, "Visualizar registros de Perfis."),
    SIT_PERFIL(7, "Atualizar situação dos registros de Perfis."),

    CAD_EVENTO(4, "Cadastrar  registros de Evento."),
    ALT_EVENTO(5, "Alterar o registro de Evento."),
    VIS_EVENTO(6, "Visualizar registros de Evento."),
    SIT_EVENTO(7, "Atualizar situação dos registros de Evento.");


    private final Integer valor;
    private final String descricao;

    public static PermissaoEnum porValor(int valor) {
        return Stream.of(PermissaoEnum.values())
                .filter(t -> t.getValor() == valor)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Valor Enum invalido: " + valor));
    }

    public static PermissaoEnum porDescricao(String descricao) {
        return Stream.of(PermissaoEnum.values())
                .filter(t -> t.getDescricao().equalsIgnoreCase(descricao))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Descrição Enum invalido: " + descricao));
    }

}