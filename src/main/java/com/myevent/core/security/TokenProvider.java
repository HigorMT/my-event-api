package com.myevent.core.security;

import com.myevent.api.dto.system.auth.AuthUser;
import com.myevent.domain.entity.Usuario;

import com.myevent.domain.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final UsuarioService usuarioService;

    @Value("${my-event.secret-key}")
    private String secretKey;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getClaims(token);

        return claimsResolver.apply(claims);
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public String generateToken(AuthUser userInfo) {
        try {
            return Jwts.builder()
                    .setSubject(userInfo.getUsuario().getCredencial())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 43200000))
                    .signWith(generateKey())
                    .compact();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String subject) {
        try {
            return Jwts.builder()
                    .setSubject(subject)
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + 43200000))
                    .signWith(generateKey())
                    .compact();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private Key generateKey() throws NoSuchAlgorithmException {
        return new SecretKeySpec(
                MessageDigest.getInstance("SHA-256").digest(this.secretKey.getBytes(UTF_8)),
                HS256.getJcaName()
        );
    }

    public Usuario getUsuarioLogado() {
        String loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return usuarioService.findByUserName(loggedUser);
    }

    public Usuario getUsuarioLogadoOrNull() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (isNull(authentication))
            return null;

        String loggedUser = authentication.getPrincipal().toString();

        return usuarioService.findByUserNameOrNull(loggedUser);
    }

}
