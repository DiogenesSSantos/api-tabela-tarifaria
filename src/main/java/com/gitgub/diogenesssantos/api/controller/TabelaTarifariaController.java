package com.gitgub.diogenesssantos.api.controller;

import com.gitgub.diogenesssantos.api.assemble.AssembleTabelaTarifaria;
import com.gitgub.diogenesssantos.api.docs.TabelaTarifariaDocumentacaoOpenAPI;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaResponseDTO;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.service.TabelaTarifariaService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<TabelaTarifariaResponseDTO> buscarPorId(@PathVariable (name = "id", required =  true) Long id) {
        var tabelaTarifa = service.buscarPorId(id);
        var tabelaTarifaDTO = AssembleTabelaTarifaria.modelToDTO(tabelaTarifa);
        return ResponseEntity.ok().body(tabelaTarifaDTO);
    }


    @PostMapping
    public ResponseEntity<TabelaTarifaria> criarTabela(@RequestBody TabelaTarifariaRequestDTO tabelaTarifariaRequest) {
        TabelaTarifaria tabela = service.salvarTabela(tabelaTarifariaRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(tabela);
    }

    @PostMapping("/lote")
    public ResponseEntity< List<TabelaTarifaria>> criarTabelaEmLote(@RequestBody
                                                           List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests) {
        List<TabelaTarifaria> tabelas = service.salvarTabelaEmLote(tabelaTarifariaRequests);
        return ResponseEntity.status(HttpStatus.CREATED).body(tabelas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TabelaTarifariaResponseDTO> deletaTabelaPorId(@PathVariable (name = "id") Long id) {
        TabelaTarifaria tabelaTarifariaBD = service.deletarPorId(id);
        var tabelaTarifaDTO = AssembleTabelaTarifaria.modelToDTO(tabelaTarifariaBD);
        return ResponseEntity.ok().body(tabelaTarifaDTO);
    }

}