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
    private final FaixaTarifariaRepository faixaRepo;

    public TabelaTarifariaService(TabelaTarifariaRepository tabelaRepo, FaixaTarifariaRepository faixaRepo) {
        this.tabelaRepo = tabelaRepo;
        this.faixaRepo = faixaRepo;

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
        validaFaixasTabelaTarifaria(tabelaTarifariaRDTO);

        TabelaTarifaria tabela = new TabelaTarifaria();
        tabela.setNome(tabelaTarifariaRDTO.nome());
        tabela.setDataVigencia(tabelaTarifariaRDTO.dataVigencia());
        tabela.setAtivo(true);

        TabelaTarifaria tabelaSalva = tabelaRepo.saveAndFlush(tabela);
        List<FaixaTarifaria> faixaTarifariaList = criarFaixaTarifariaList(tabelaTarifariaRDTO, tabelaSalva);

        faixaRepo.saveAll(faixaTarifariaList);

        return tabela;

    }


    @Transactional
    public List<TabelaTarifaria> salvarTabelaEmLote(List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests) {
        tabelaTarifariaRequests.forEach(this::validaFaixasTabelaTarifaria);

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
            TabelaTarifaria tabelaTarifaria = tabelasSalvas.get(i);
            faixaTarifariaList = criarFaixaTarifariaList(tabelaTarifariaRDTO, tabelaTarifaria);

        }

        faixaRepo.saveAll(faixaTarifariaList);

        return tabelasSalvas;

    }


    @Transactional
    public TabelaTarifaria deletarPorId(Long id) {
        TabelaTarifaria tabelaTarifariaBD = buscarPorId(id);
        tabelaTarifariaBD.setAtivo(false);
        return tabelaRepo.saveAndFlush(tabelaTarifariaBD);

    }


    private void validaFaixasTabelaTarifaria(TabelaTarifariaRequestDTO tabelaTarifaria) {

        for (CategoriaRequestDTO categoria : tabelaTarifaria.categorias()) {
            var categoriaNome = categoria.nome();
            var faixas = categoria.faixas();

            if (faixas.isEmpty())
                throw new FaixaTarifariaException(
                        "Erro na criação da faixa tarifaria da tabela " + tabelaTarifaria.nome(), categoriaNome);

            Integer fimAnterior = null;
            Integer ordemAnterior = null;

            for (var faixa : faixas) {
                int inicio = faixa.inicio();
                int fim = faixa.fim();
                int ordem = faixa.ordem();

                if (fim <= inicio)
                    throw new FaixaTarifariaValidacaoCamposException(
                            "Erro: fim deve ser maior que inicio na categoria ", categoriaNome);

                if (ordemAnterior != null && ordem <= ordemAnterior)
                    throw new FaixaTarifariaValidacaoCamposException(
                            "Erro no campo ordem da categoria ", categoriaNome);

                if (fimAnterior == null) {
                    if (inicio != 0)
                        throw new FaixaTarifariaValidacaoCamposException(
                                "Erro: a primeira faixa deve começar com inicio = 0 na categoria ", categoriaNome);
                } else {
                    if (inicio != fimAnterior + 1)
                        throw new FaixaTarifariaValidacaoCamposException(
                                "Erro no campo inicio : o inicio da faixa atual deve ser  " +
                                        "> que fim da faixa anterior pelo menos +1 na categoria ", categoriaNome);
                }

                fimAnterior = fim;
                ordemAnterior = ordem;
            }
        }

    }

    private List<FaixaTarifaria> criarFaixaTarifariaList(TabelaTarifariaRequestDTO tabelaTarifaria, TabelaTarifaria tabela) {
        List<FaixaTarifaria> faixaTarifariaList = new ArrayList<>();
        for (CategoriaRequestDTO catReq : tabelaTarifaria.categorias()) {
            Categoria categoria = Categoria.valueOf(catReq.nome().toUpperCase());

            for (FaixaRequestDTO faixaReq : catReq.faixas()) {
                FaixaTarifaria faixa = new FaixaTarifaria();
                faixa.setTabela(tabela);
                faixa.setCategoria(categoria);
                faixa.setInicio(faixaReq.inicio());
                faixa.setFim(faixaReq.fim());
                faixa.setValorUnitario(BigDecimal.valueOf(faixaReq.valorUnitario()));
                faixa.setOrdem(faixaReq.ordem());
                faixaTarifariaList.add(faixa);
            }
        }
        return faixaTarifariaList;
    }




}