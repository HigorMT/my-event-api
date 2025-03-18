package com.myevent.common.email;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Singular;
import org.springframework.validation.annotation.Validated;

import java.util.Map;

@Getter
@Builder
@Validated
public class MensagemProperties {

    @NonNull
    private String destinatario;

    @NonNull
    private String assunto;

    @NonNull
    private String template;

    @Singular("model")
    private Map<String, Object> models;

}
