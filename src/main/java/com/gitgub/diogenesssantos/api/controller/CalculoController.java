package com.gitgub.diogenesssantos.api.controller;

import com.gitgub.diogenesssantos.api.docs.CalculoDocumentacaoOpenAPI;
import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoResponseDTO;
import com.gitgub.diogenesssantos.api.service.CalculoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CalculoController implements CalculoDocumentacaoOpenAPI {

    private final CalculoService calculoService;
    public CalculoController(CalculoService calculoService) {
        this.calculoService = calculoService; }

    @PostMapping("/calculos")
    public ResponseEntity<CalculoResponseDTO> calcular(@RequestBody CalculoRequestDTO req) {

        var resp = calculoService.calcular(req);
        return ResponseEntity.ok(resp);
    }

}