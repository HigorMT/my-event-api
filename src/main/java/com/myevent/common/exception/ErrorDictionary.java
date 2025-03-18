package com.myevent.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorDictionary {

    EXPIRED_TOKEN(415, "Token de autenticação expirado."),
    PERMISSION_NOT_ALLOWED(403, "Usuário não Possui Permissão para Acessar Essa Funcionalidade"),
    CREDENTIAL_NOT_ALLOWED(403, "Usuário não Possui Credencial para Acessar Essa Funcionalidade"),

    ERRO_GENERICO(500, "Falha na requisição, se o erro persistir contate o administrador.");

    private final int code;
    private final String message;

}
