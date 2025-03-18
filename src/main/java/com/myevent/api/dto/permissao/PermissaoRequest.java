package com.myevent.api.dto.permissao;

import com.myevent.domain.enums.PermissaoEnum;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoRequest {

    @NotNull
    private Long id;

    @NotNull
    private PermissaoEnum permissao;

    private String descricao;

}
