package com.myevent.api.dto.usuario;

import com.myevent.domain.enums.StatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioFilter {

    private String nome;
    private String email;
    private String credencial;
    private StatusEnum status;

}
