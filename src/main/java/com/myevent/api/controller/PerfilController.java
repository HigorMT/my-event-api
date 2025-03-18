package com.myevent.api.controller;

import com.myevent.api.converter.PerfilConverter;
import com.myevent.api.dto.perfil.PerfilFilter;
import com.myevent.api.dto.perfil.PerfilRequest;
import com.myevent.api.dto.perfil.PerfilResponse;
import com.myevent.api.dto.perfil.PerfilResume;
import com.myevent.common.anotations.Auth;
import com.myevent.domain.entity.Perfil;
import com.myevent.domain.service.PerfilService;
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

import java.util.List;

import static com.myevent.domain.enums.PermissaoEnum.ALT_PERFIL;
import static com.myevent.domain.enums.PermissaoEnum.CAD_PERFIL;
import static com.myevent.domain.enums.PermissaoEnum.SIT_PERFIL;
import static com.myevent.domain.enums.PermissaoEnum.VIS_PERFIL;

@RestController
@RequiredArgsConstructor
@RequestMapping("/perfil")
public class PerfilController {

    private final PerfilConverter converter;
    private final PerfilService service;

    @GetMapping
    @Auth(permission = VIS_PERFIL)
    public ResponseEntity<List<PerfilResume>> findById() {
        List<PerfilResume> resumes = service.findAllActives();
        return ResponseEntity.ok(resumes);
    }

    @GetMapping("/{id}")
    @Auth(permission = VIS_PERFIL)
    public ResponseEntity<PerfilResponse> findById(@PathVariable("id") Long id) {
        Perfil entity = service.findById(id);
        PerfilResponse response = converter.toResponse(entity);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Auth(permission = CAD_PERFIL)
    public ResponseEntity<PerfilResponse> save(@Valid @RequestBody PerfilRequest request) {
        Perfil user = converter.toEntity(request);
        Perfil savedUser = service.save(user);
        return ResponseEntity.ok(converter.toResponse(savedUser));
    }

    @PutMapping("/{id}")
    @Auth(permission = ALT_PERFIL)
    public ResponseEntity<PerfilResponse> update(@PathVariable("id") Long id, @Valid @RequestBody PerfilRequest request) {
        Perfil entity = converter.toEntity(request);
        Perfil savedEntity = service.update(entity, id);
        PerfilResponse response = converter.toResponse(savedEntity);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/ativar")
    @Auth(permission = SIT_PERFIL)
    public ResponseEntity<Void> ativar(@PathVariable("id") Long id) {
        service.ativar(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/inativar")
    @Auth(permission = SIT_PERFIL)
    public ResponseEntity<Void> inativar(@PathVariable("id") Long id) {
        service.inativar(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/paginado")
    public ResponseEntity<Page<PerfilResume>> pageable(PerfilFilter filter, @PageableDefault Pageable pageable) {
        Page<PerfilResume> resumes = service.pageable(filter, pageable);
        return ResponseEntity.ok(resumes);
    }

}
