package com.myevent.domain.service.auth;


import com.myevent.api.dto.system.auth.AuthUser;
import com.myevent.domain.entity.Usuario;
import com.myevent.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioService.findByUserNameOrNull(username);

        if (isNull(usuario)) {
            throw new UsernameNotFoundException("Credenciais inválidas.");
        }

        return new AuthUser(usuario);
    }

}
