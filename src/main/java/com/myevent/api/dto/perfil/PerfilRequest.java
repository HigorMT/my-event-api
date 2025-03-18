package com.myevent.api.dto.perfil;

import com.myevent.api.dto.permissao.PermissaoRequest;
import com.myevent.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilRequest {

    private Long id;
    private String nome;
    private boolean padrao;
    private String descricao;
    private StatusEnum status;
    private List<PermissaoRequest> permissoes;

}
