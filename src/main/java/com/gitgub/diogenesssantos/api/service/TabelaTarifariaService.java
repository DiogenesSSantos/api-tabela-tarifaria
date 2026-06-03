package com.gitgub.diogenesssantos.api.service;

import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.CategoriaRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.FaixaRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequestDTO;
import com.gitgub.diogenesssantos.api.exception.*;
import com.gitgub.diogenesssantos.api.model.Categoria;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.repository.FaixaTarifariaRepository;
import com.gitgub.diogenesssantos.api.repository.TabelaTarifariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class TabelaTarifariaService {

    private final TabelaTarifariaRepository tabelaRepo;
    private final FaixaTarifariaService faixaTarifariaService;
    public TabelaTarifariaService(TabelaTarifariaRepository tabelaRepo, FaixaTarifariaService faixaTarifariaService) {
        this.tabelaRepo = tabelaRepo;
        this.faixaTarifariaService = faixaTarifariaService;

    }

    public List<TabelaTarifaria> buscarTodasTabelasAtivas() {
        return tabelaRepo.findByAtivoTrueOrderByDataVigenciaDesc();

    }

    public TabelaTarifaria buscarPorId(Long id) {
        return tabelaRepo.buscarPorId(id)
                .orElseThrow(() ->
                        new TabelaTarifariaNaoLocalizadaException(
                                String.format("A tabela tarifaria de id %d não existe no banco de dados.", id)));

    }

    @Transactional
    public TabelaTarifaria salvarTabela(TabelaTarifariaRequestDTO tabelaTarifariaRDTO) {
        faixaTarifariaService.validaFaixasTabelaTarifaria(tabelaTarifariaRDTO);

        TabelaTarifaria tabela = new TabelaTarifaria();
        tabela.setNome(tabelaTarifariaRDTO.nome());
        tabela.setDataVigencia(tabelaTarifariaRDTO.dataVigencia());
        tabela.setAtivo(true);

        TabelaTarifaria tabelaSalva = tabelaRepo.saveAndFlush(tabela);
        List<FaixaTarifaria> faixaTarifariaList = faixaTarifariaService.
                criarFaixaTarifariaList(tabelaTarifariaRDTO, tabelaSalva);

        faixaTarifariaService.salvarTodasFaixas(faixaTarifariaList);

        return tabela;

    }


    @Transactional
    public List<TabelaTarifaria> salvarTabelaEmLote(List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests) {
        tabelaTarifariaRequests.forEach(faixaTarifariaService::validaFaixasTabelaTarifaria);

        List<TabelaTarifaria> tabelas = tabelaTarifariaRequests.stream()
                .map(req -> {
                    TabelaTarifaria tabela = new TabelaTarifaria();
                    tabela.setNome(req.nome());
                    tabela.setDataVigencia(req.dataVigencia());
                    tabela.setAtivo(true);
                    return tabela;
                })
                .toList();

        List<TabelaTarifaria> tabelasSalvas = tabelaRepo.saveAllAndFlush(tabelas);
        List<FaixaTarifaria> faixaTarifariaList = new ArrayList<>();

        for (int i = 0; i < tabelasSalvas.size(); i++) {
            TabelaTarifariaRequestDTO tabelaTarifariaRDTO = tabelaTarifariaRequests.get(i);
            TabelaTarifaria tabelaSalva = tabelasSalvas.get(i);
            faixaTarifariaList = faixaTarifariaService.
                    criarFaixaTarifariaList(tabelaTarifariaRDTO, tabelaSalva);

        }

        faixaTarifariaService.salvarTodasFaixas(faixaTarifariaList);

        return tabelasSalvas;

    }


    @Transactional
    public TabelaTarifaria deletarPorId(Long id) {
        TabelaTarifaria tabelaTarifariaBD = buscarPorId(id);
        tabelaTarifariaBD.setAtivo(false);
        return tabelaRepo.saveAndFlush(tabelaTarifariaBD);

    }




}