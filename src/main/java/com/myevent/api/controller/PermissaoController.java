package com.myevent.api.controller;

import com.myevent.api.converter.PermissaoConverter;
import com.myevent.api.dto.permissao.PermissaoResponse;
import com.myevent.domain.service.PermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/permissao")
public class PermissaoController {

    private final PermissaoConverter converter;
    private final PermissaoService service;

    @GetMapping
    public ResponseEntity<List<PermissaoResponse>> findById() {
        List<PermissaoResponse> response = converter.toResponse(service.selectAll());
        return ResponseEntity.ok(response);
    }

}
