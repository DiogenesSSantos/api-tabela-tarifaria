package com.gitgub.diogenesssantos.api.controller;

import com.gitgub.diogenesssantos.api.docs.TabelaTarifariaDocumentacaoOpenAPI;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequestDTO;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.service.TabelaTarifariaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tabelas-tarifarias")
public class TabelaTarifariaController implements TabelaTarifariaDocumentacaoOpenAPI {

    private final TabelaTarifariaService service;

    public TabelaTarifariaController(TabelaTarifariaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TabelaTarifaria>> listarTabelas() {
        return ResponseEntity.ok(service.buscarTodasTabelas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabelaTarifaria> buscarPorId(@PathVariable (name = "id", required =  true) Long id) {
        var tabelaTarifa = service.buscarPorId(id);
        return ResponseEntity.ok().body(tabelaTarifa);
    }


    @PostMapping
    public ResponseEntity<TabelaTarifaria> criarTabela(@RequestBody TabelaTarifariaRequestDTO tabelaTarifariaRequest) {
        TabelaTarifaria tabela = service.salvarTabela(tabelaTarifariaRequest);
        return ResponseEntity.ok(tabela);
    }

    @PostMapping("/lote")
    public ResponseEntity< List<TabelaTarifaria>> criarTabelaEmLote(@RequestBody
                                                           List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests) {
        List<TabelaTarifaria> tabela = service.salvarTabelaEmLote(tabelaTarifariaRequests);
        return ResponseEntity.ok(tabela);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaTabelaPorId(@PathVariable (name = "id") Long id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

}