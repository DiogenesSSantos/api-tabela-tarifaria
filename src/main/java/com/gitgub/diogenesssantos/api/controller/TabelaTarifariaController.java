package com.gitgub.diogenesssantos.api.controller;

import com.gitgub.diogenesssantos.api.assemble.AssembleTabelaTarifaria;
import com.gitgub.diogenesssantos.api.docs.TabelaTarifariaDocumentacaoOpenAPI;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaResponseDTO;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.service.TabelaTarifariaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<TabelaTarifariaResponseDTO>> buscarTodasTabelas() {
        List<TabelaTarifaria> tabelaTarifariaListBD = service.buscarTodasTabelasAtivas();
        var listtabelaTarifaDTO = AssembleTabelaTarifaria.listModelToLisDTO(tabelaTarifariaListBD);
        return ResponseEntity.ok().body(listtabelaTarifaDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TabelaTarifariaResponseDTO> buscarPorId(@PathVariable (name = "id") Long id) {
        var tabelaTarifa = service.buscarPorId(id);
        var tabelaTarifaDTO = AssembleTabelaTarifaria.modelToDTO(tabelaTarifa);
        return ResponseEntity.ok().body(tabelaTarifaDTO);
    }


    @PostMapping
    public ResponseEntity<TabelaTarifariaResponseDTO>  criarTabela(@RequestBody @Valid TabelaTarifariaRequestDTO tabelaTarifariaRequest) {
        TabelaTarifaria tabelaTarifariaBD = service.salvarTabela(tabelaTarifariaRequest);
        var tabelaTarifaDTO = AssembleTabelaTarifaria.modelToDTO(tabelaTarifariaBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(tabelaTarifaDTO);
    }

    @PostMapping("/lote")
    public ResponseEntity<List<TabelaTarifariaResponseDTO>> criarTabelaEmLote(@RequestBody
                                                           List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests) {
        List<TabelaTarifaria> tabelaTarifariaListBD = service.salvarTabelaEmLote(tabelaTarifariaRequests);
        var listtabelaTarifaDTO = AssembleTabelaTarifaria.listModelToLisDTO(tabelaTarifariaListBD);
        return ResponseEntity.status(HttpStatus.CREATED).body(listtabelaTarifaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TabelaTarifariaResponseDTO> deletaTabelaPorId(@PathVariable (name = "id") Long id) {
        TabelaTarifaria tabelaTarifariaBD = service.deletarPorId(id);
        var tabelaTarifaDTO = AssembleTabelaTarifaria.modelToDTO(tabelaTarifariaBD);
        return ResponseEntity.ok().body(tabelaTarifaDTO);
    }

}