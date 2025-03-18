package com.myevent.api.dto.permissao;

import com.myevent.domain.enums.PermissaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoResponse {

    private Long id;
    private String descricao;
    private PermissaoEnum permissao;

}
