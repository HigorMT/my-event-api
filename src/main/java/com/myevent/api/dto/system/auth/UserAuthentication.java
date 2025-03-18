package com.myevent.api.dto.system.auth;

import com.myevent.domain.entity.Usuario;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
public class UserAuthentication implements Authentication {

    private final Usuario user;

    public UserAuthentication(Usuario user) {
        this.user = user;
    }

    //<editor-fold desc="Overrides Methods" defaultstate="collapsed">

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return this.user;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.user.getCredencial();
    }

    @Override
    public boolean isAuthenticated() {
        return !isNull(this.user);
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return this.user.getCredencial();
    }

    //</editor-fold>

}
