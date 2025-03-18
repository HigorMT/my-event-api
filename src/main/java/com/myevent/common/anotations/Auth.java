package com.myevent.common.anotations;

import com.myevent.domain.enums.PermissaoEnum;
import jakarta.validation.Payload;
import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Anotação Personalizada para a verificação das Permissões de Acesso do Usuário
 * */
@Documented
@Retention(RUNTIME)
@Repeatable(Auth.List.class)
@Target({TYPE, FIELD, METHOD, ANNOTATION_TYPE})
public @interface Auth {

    String message() default "Usuario não Possui Permissão para Acessar essa Funcionalidade.";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    @PreAuthorize("@permissionValidator.validatePermission({#permission()})")
    PermissaoEnum permission();

    @Documented
    @Target({FIELD, METHOD, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @interface List {
        Auth[] value();
    }

}
