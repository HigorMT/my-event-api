package com.myevent.api.controller;

import com.myevent.domain.service.IngressoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ingresso")
public class IngressoController {

    private final IngressoService service;

    @GetMapping(produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> impressoesIngresso() {
        return ResponseEntity.ok(service.impressaoIngresso());
    }

}
