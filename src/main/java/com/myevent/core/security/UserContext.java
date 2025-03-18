package com.myevent.core.security;


import com.myevent.common.exception.AuthException;
import com.myevent.common.util.SpringContext;
import com.myevent.domain.entity.Perfil;
import com.myevent.domain.entity.Permissao;
import com.myevent.domain.entity.Usuario;
import com.myevent.domain.enums.PermissaoEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.myevent.common.exception.ErrorDictionary.CREDENTIAL_NOT_ALLOWED;
import static com.myevent.common.exception.ErrorDictionary.PERMISSION_NOT_ALLOWED;


@Component
public class UserContext {

    public Usuario getLoggedUser() {
        Usuario loggedUser = SpringContext.getBean(TokenProvider.class).getUsuarioLogado();
        if (loggedUser == null || loggedUser.getPerfil() == null) {
            throw new AuthException(CREDENTIAL_NOT_ALLOWED);
        }
        return loggedUser;
    }

    public Perfil getLoggedUserProfile() {
        Usuario loggedUser = getLoggedUser();
        return loggedUser.getPerfil();
    }

    public List<PermissaoEnum> getLoggedUserPermissions() {
        Perfil perfil = getLoggedUserProfile();
        if (perfil.getPermissoes().isEmpty()) {
            throw new AuthException(PERMISSION_NOT_ALLOWED);
        }

        return perfil.getPermissoes().stream()
                .map(Permissao::getPermissao)
//                .map(Enum::toString)
                .collect(Collectors.toList());
    }

}
