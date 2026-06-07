package com.gitgub.diogenesssantos.api.service;

import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequestDTO;
import com.gitgub.diogenesssantos.api.exception.TabelaTarifariaNaoLocalizadaException;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.repository.TabelaTarifariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TabelaTarifariaService {

    private final TabelaTarifariaRepository tabelaRepo;
    private final FaixaTarifariaService faixaTarifariaService;

    public TabelaTarifariaService(TabelaTarifariaRepository tabelaRepo, FaixaTarifariaService faixaTarifariaService) {
        this.tabelaRepo = tabelaRepo;
        this.faixaTarifariaService = faixaTarifariaService;

    }

    public List<TabelaTarifaria> buscarTodasTabelasAtivas() {
        List<TabelaTarifaria> byAtivoTrueOrderByDataVigenciaDesc = tabelaRepo.buscarTodasAtivas();
        return byAtivoTrueOrderByDataVigenciaDesc.stream().toList();

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
        TabelaTarifaria tabela = dtoToModel(tabelaTarifariaRDTO);

        TabelaTarifaria tabelaSalva = tabelaRepo.saveAndFlush(tabela);
        List<FaixaTarifaria> faixaTarifariaList = faixaTarifariaService.criarFaixaTarifariaList(tabelaTarifariaRDTO,
                tabelaSalva);

        faixaTarifariaService.salvarTodasFaixas(faixaTarifariaList);

        return tabela;

    }



    @Transactional
    public List<TabelaTarifaria> salvarTabelaEmLote(List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests) {
        tabelaTarifariaRequests.forEach(faixaTarifariaService::validaFaixasTabelaTarifaria);

        List<TabelaTarifaria> tabelas = tabelaTarifariaRequests.stream()
                .map(this::dtoToModel)
                .toList();

        List<TabelaTarifaria> tabelasSalvas = tabelaRepo.saveAllAndFlush(tabelas);
        List<FaixaTarifaria> todasFaixas = new ArrayList<>();

        for (int i = 0; i < tabelasSalvas.size(); i++) {
            List<FaixaTarifaria> faixas = faixaTarifariaService.criarFaixaTarifariaList(tabelaTarifariaRequests.get(i),
                    tabelasSalvas.get(i));

            todasFaixas.addAll(faixas);
        }

        faixaTarifariaService.salvarTodasFaixas(todasFaixas);

        return tabelasSalvas;
    }


    @Transactional
    public TabelaTarifaria deletarPorId(Long id) {
        TabelaTarifaria tabelaTarifariaBD = buscarPorId(id);
        tabelaTarifariaBD.setAtivo(false);
        return tabelaRepo.saveAndFlush(tabelaTarifariaBD);

    }

    private TabelaTarifaria dtoToModel(TabelaTarifariaRequestDTO tabelaTarifariaRDTO) {
        TabelaTarifaria tabela = new TabelaTarifaria();
        tabela.setNome(tabelaTarifariaRDTO.nome());
        tabela.setDataVigencia(tabelaTarifariaRDTO.dataVigencia());
        tabela.setAtivo(true);
        return tabela;
    }


}