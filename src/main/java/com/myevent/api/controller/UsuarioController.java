package com.myevent.api.controller;

import com.myevent.api.converter.UsuarioConverter;
import com.myevent.api.dto.usuario.UsuarioFilter;
import com.myevent.api.dto.usuario.UsuarioRegisterRequest;
import com.myevent.api.dto.usuario.UsuarioRequest;
import com.myevent.api.dto.usuario.UsuarioResponse;
import com.myevent.api.dto.usuario.UsuarioResume;
import com.myevent.common.anotations.Auth;
import com.myevent.domain.entity.Usuario;
import com.myevent.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.myevent.domain.enums.PermissaoEnum.ALT_USUARIO;
import static com.myevent.domain.enums.PermissaoEnum.CAD_USUARIO;
import static com.myevent.domain.enums.PermissaoEnum.SIT_USUARIO;
import static com.myevent.domain.enums.PermissaoEnum.VIS_USUARIO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioConverter converter;
    private final UsuarioService service;

    @GetMapping("/{id}")
    @Auth(permission = VIS_USUARIO)
    public ResponseEntity<UsuarioResponse> findById(@PathVariable("id") Long id) {
        Usuario entity = service.findById(id);
        UsuarioResponse response = converter.toResponse(entity);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Auth(permission = CAD_USUARIO)
    public ResponseEntity<UsuarioResponse> save(@Valid @RequestBody UsuarioRegisterRequest request) {
        Usuario user = converter.toEntity(request);
        Usuario savedUser = service.save(user);
        return ResponseEntity.ok(converter.toResponse(savedUser));
    }

    @PutMapping("/{id}")
    @Auth(permission = ALT_USUARIO)
    public ResponseEntity<UsuarioResponse> update(@PathVariable("id") Long id, @Valid @RequestBody UsuarioRequest request) {
        Usuario entity = converter.toEntity(request);
        Usuario savedEntity = service.update(entity, id);
        UsuarioResponse response = converter.toResponse(savedEntity);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/ativar")
    @Auth(permission = SIT_USUARIO)
    public ResponseEntity<Void> ativar(@PathVariable("id") Long id) {
        service.ativar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/inativar")
    @Auth(permission = SIT_USUARIO)
    public ResponseEntity<Void> inativar(@PathVariable("id") Long id) {
        service.inativar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<UsuarioResume>> pageable(UsuarioFilter filter, @PageableDefault Pageable pageable) {
        Page<UsuarioResume> resumes = service.pageable(filter, pageable);
        return ResponseEntity.ok(resumes);
    }

}
