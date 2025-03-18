package com.myevent.common.listener;

import com.myevent.core.security.TokenProvider;
import com.myevent.domain.entity.Usuario;
import com.myevent.domain.entity.audit.AuditRev;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Component;

import static com.myevent.common.util.SpringContext.getBean;
import static java.util.Objects.isNull;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class EntityRevisionListener implements RevisionListener {

    private final HttpServletRequest httpServletRequest;

    @Override
    @SneakyThrows
    public void newRevision(Object revisionEntity) {
        Usuario loggedUser = getBean(TokenProvider.class).getUsuarioLogadoOrNull();

        AuditRev revisao = (AuditRev) revisionEntity;
        revisao.setEntidade(ContextLookup.getCurrentEntity().getClass().getSimpleName());
        revisao.setTipoRevisao(ContextLookup.getCurrentRevisionType());

        revisao.setIp(httpServletRequest.getRemoteAddr());
        if (!isNull(loggedUser)) {
            revisao.setUsuario(loggedUser);
        } else {
            revisao.setDeviceReq(true);
        }

    }

}
