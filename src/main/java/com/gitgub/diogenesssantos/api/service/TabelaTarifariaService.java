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

    @Transactional
    public TabelaTarifaria salvarTabela(TabelaTarifariaRequestDTO tabelaTarifaria) {
        validaTabelaTarifariaRequest(tabelaTarifaria);

        TabelaTarifaria tabela = new TabelaTarifaria();
        tabela.setNome(tabelaTarifaria
                .nome());
        tabela.setDataVigencia(tabelaTarifaria
                .dataVigencia());
        tabela.setAtivo(true);
        tabelaRepo.save(tabela);

        for (CategoriaRequestDTO catReq : tabelaTarifaria
                .categorias()) {

            Categoria categoria = Categoria.valueOf(catReq.nome().toUpperCase());

            for (FaixaRequestDTO faixaReq : catReq.faixas()) {
                FaixaTarifaria faixa = new FaixaTarifaria();
                faixa.setTabela(tabela);
                faixa.setCategoria(categoria);
                faixa.setInicio(faixaReq.inicio());
                faixa.setFim(faixaReq.fim());
                faixa.setValorUnitario(BigDecimal.valueOf(faixaReq.valorUnitario()));
                faixa.setOrdem(faixaReq.ordem());
                faixaRepo.save(faixa);
            }
        }

        return tabela;
    }


    public List<TabelaTarifaria> buscarTodasTabelas() {
        return tabelaRepo.findAll().stream()
                .sorted(Comparator.comparing(TabelaTarifaria::getDataVigencia)
                        .reversed())
                .toList();

    }

    public List<TabelaTarifaria> salvarTabelaEmLote(List<TabelaTarifariaRequestDTO> tabelaTarifariaRequests) {
        List<TabelaTarifaria> tabelaTarifariaList = new ArrayList<>();

        for (var tabelaTarifaria : tabelaTarifariaRequests) {
            var tabelaTarSalvaBD = salvarTabela(tabelaTarifaria);
            tabelaTarifariaList.add(tabelaTarSalvaBD);
        }

        return tabelaTarifariaList;

    }


    private void validaTabelaTarifariaRequest(TabelaTarifariaRequestDTO tabelaTarifaria) {
        validaCategoriaTabelaTarafia(tabelaTarifaria);
        validaFaixasTabelaTarifaria(tabelaTarifaria);
    }

    private void validaFaixasTabelaTarifaria(TabelaTarifariaRequestDTO tabelaTarifaria) {
        Iterator<CategoriaRequestDTO> iterator = tabelaTarifaria.categorias().iterator();

        while (iterator.hasNext()) {
            var categoria = iterator.next();
            var categoriaNome = categoria.nome();
            var faixas = categoria.faixas();

            if (faixas.isEmpty())
                throw new FaixaTarifariaException(
                        "Erro na criação da faixa tarifaria da tabela " + tabelaTarifaria.nome(),
                        categoriaNome
                );

            Integer fimAnterior = null;
            Integer ordemAnterior = null;

            for (var faixa : faixas) {
                int inicio = faixa.inicio();
                int fim = faixa.fim();
                int ordem = faixa.ordem();

                // 1. fim deve ser estritamente maior que inicio
                if (fim <= inicio)
                    throw new FaixaTarifariaValidacaoCamposException(
                            "Erro: fim deve ser maior que inicio na categoria ", categoriaNome
                    );

                if (ordemAnterior != null && ordem <= ordemAnterior)
                    throw new FaixaTarifariaValidacaoCamposException(
                            "Erro no campo ordem da categoria ", categoriaNome
                    );

                if (fimAnterior == null) {
                    if (inicio != 0)
                        throw new FaixaTarifariaValidacaoCamposException(
                                "Erro: a primeira faixa deve começar com inicio = 0 na categoria ", categoriaNome
                        );
                } else {
                    if (inicio != fimAnterior + 1)
                        throw new FaixaTarifariaValidacaoCamposException(
                                "Erro no campo inicio : o inicio da faixa atual deve ser  " +
                                        "> que fim da faixa anterior pelo menos +1 na categoria ", categoriaNome
                        );
                }

                fimAnterior = fim;
                ordemAnterior = ordem;
            }
        }


    }


    private static void validaCategoriaTabelaTarafia(TabelaTarifariaRequestDTO tabelaTarifaria) {
        validaCamposTabelaTarifaria(tabelaTarifaria);


        if (tabelaTarifaria.categorias().size() < 4) {
            throw new TabelaTarifariaException("Erro tarefa", tabelaTarifaria.nome());
        }


        Iterator<CategoriaRequestDTO> iterator = tabelaTarifaria
                .categorias().iterator();
        while (iterator.hasNext()) {
            var categoria = iterator.next();
            var categoriaNome = categoria.nome();

            if (categoria == null || categoriaNome == null || categoriaNome.isBlank()) throw new
                    CategoriaNomeException("A categoria não pode null ou vázio.");

            boolean validaNome = switch (categoria.nome()) {
                case "COMERCIAL", "INDUSTRIAL", "PARTICULAR", "PUBLICO" -> true;
                default -> false;
            };

            if (!validaNome) throw new
                    CategoriaNomeInvalidoException("A nome categoria inválido.", categoriaNome);
        }
    }

    private static void validaCamposTabelaTarifaria(TabelaTarifariaRequestDTO tabelaTarifaria) {
        if (tabelaTarifaria.categorias() == null) {
            throw new TabelaTarifariaNullException("Erro na tabela tarifaria, categoria não pode ser null.");
        }

        if (tabelaTarifaria.nome() == null || tabelaTarifaria.nome().isBlank()) {
            throw new TabelaTarifariaNullException("Erro na tabela tarifaria, o nome não pode ser null ou ser vázia.");
        }

        if (tabelaTarifaria.dataVigencia() == null) {
            throw new TabelaTarifariaDataVigenciaException("Erro na tabela tarifaria, a data não pode ser null.");
        }
    }


    public void deletarPorId(Long id) {
        tabelaRepo.findById(id)
                .ifPresent(tabelaRepo::delete);
    }

    public TabelaTarifaria buscarPorId(Long id) {
        return tabelaRepo.findById(id)
                .orElseThrow(() ->
                        new TabelaTarifariaNaoLocalizadaException(
                                String.format("A tabela tarifaria de id %d " +
                                        "não existe no banco de dados.", id)));


    }
}