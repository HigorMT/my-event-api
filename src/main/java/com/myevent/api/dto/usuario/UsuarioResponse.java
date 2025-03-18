package com.myevent.api.dto.usuario;

import com.myevent.api.dto.perfil.PerfilResume;
import com.myevent.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String email;
    private String credencial;
    private StatusEnum status;
    private PerfilResume perfil;

}
