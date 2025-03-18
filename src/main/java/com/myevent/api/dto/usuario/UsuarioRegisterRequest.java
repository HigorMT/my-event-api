package com.myevent.api.dto.usuario;

import com.myevent.domain.enums.StatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.myevent.domain.enums.StatusEnum.ATIVO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRegisterRequest {

    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private String credencial;

    private StatusEnum status = ATIVO;

    @NotNull
    private Long perfilId;

}
