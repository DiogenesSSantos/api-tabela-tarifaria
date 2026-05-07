package com.gitgub.diogenesssantos.api.controller;

import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequest;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.service.TabelaTarifariaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tabelas-tarifarias")
public class TabelaTarifariaController {

    private final TabelaTarifariaService service;

    public TabelaTarifariaController(TabelaTarifariaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TabelaTarifaria> criarTabela(@RequestBody TabelaTarifariaRequest tabelaTarifariaRequest) {
        TabelaTarifaria tabela = service.salvarTabela(tabelaTarifariaRequest);
        return ResponseEntity.ok(tabela);
    }

    @PostMapping("/lote")
    public ResponseEntity< List<TabelaTarifaria>> criarTabelaEmLote(@RequestBody
                                                           List<TabelaTarifariaRequest> tabelaTarifariaRequests) {
        List<TabelaTarifaria> tabela = service.salvarTabelaEmLote(tabelaTarifariaRequests);
        return ResponseEntity.ok(tabela);
    }




    @GetMapping
    public ResponseEntity<List<TabelaTarifaria>> listarTabelas() {
        return ResponseEntity.ok(service.listarTabelas());
    }

}