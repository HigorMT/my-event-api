package com.myevent.api.dto.usuario;

import com.myevent.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResume {

    private Long id;
    private String nome;
    private String email;
    private StatusEnum status;
    private String credencial;

}
