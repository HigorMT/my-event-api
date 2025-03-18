package com.myevent.api.dto.system.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {

    @NotBlank
    @Size(max = 50)
    private String credencial;

    @NotBlank
    private String senha;

}
