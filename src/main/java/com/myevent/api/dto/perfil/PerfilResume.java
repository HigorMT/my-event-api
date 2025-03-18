package com.myevent.api.dto.perfil;

import com.myevent.domain.enums.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PerfilResume {

    private Long id;
    private String nome;
    private boolean padrao;
    private String descricao;
    private StatusEnum status;

}
