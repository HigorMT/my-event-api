package com.myevent.common.anotations.validators;


import com.myevent.common.exception.AuthException;
import com.myevent.core.security.UserContext;
import com.myevent.domain.enums.PermissaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.myevent.common.exception.ErrorDictionary.CREDENTIAL_NOT_ALLOWED;
import static com.myevent.common.exception.ErrorDictionary.PERMISSION_NOT_ALLOWED;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class PermissionValidator {

    private final UserContext userContext;

    public boolean validatePermission(PermissaoEnum permission) {
        if (!isNull(permission)) {
            List<PermissaoEnum> userPermissions = userContext.getLoggedUserPermissions();
            if (userPermissions.stream().noneMatch(permission::equals)) {
                throw new AuthException(PERMISSION_NOT_ALLOWED);
            }
        }
        return true;
    }

    public boolean validateCredential(String credential) {
        String userProfile = userContext.getLoggedUserProfile().getNome().toUpperCase();
        if (!userProfile.equals(credential.toUpperCase())) {
            throw new AuthException(CREDENTIAL_NOT_ALLOWED);
        }
        return true;
    }

}
