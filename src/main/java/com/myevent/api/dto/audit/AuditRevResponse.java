package com.myevent.api.dto.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.myevent.api.dto.usuario.UsuarioResume;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.history.RevisionMetadata.RevisionType;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditRevResponse {

    private Long id;
    private String ip;
    private String entidade;
    private UsuarioResume usuario;
    private boolean system = false;
    private RevisionType tipoRevisao;
    private boolean deviceReq = false;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dtCadastro;

}
