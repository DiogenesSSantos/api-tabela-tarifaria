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
    public TabelaTarifaria salvarTabela(TabelaTarifariaRequestDTO tabelaTarifiaria) {
        validaTabelaTarifariaRequest(tabelaTarifiaria);

        TabelaTarifaria tabela = new TabelaTarifaria();
        tabela.setNome(tabelaTarifiaria
                .nome());
        tabela.setDataVigencia(tabelaTarifiaria
                .dataVigencia());
        tabela.setAtivo(true);
        tabelaRepo.save(tabela);

        for (CategoriaRequestDTO catReq : tabelaTarifiaria
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


    private void validaTabelaTarifariaRequest(TabelaTarifariaRequestDTO tabelaTarifiaria) {
        validaCategoriaTabelaTarafia(tabelaTarifiaria);
        validaFaixasTabelaTarifaria(tabelaTarifiaria);
    }

    private void validaFaixasTabelaTarifaria(TabelaTarifariaRequestDTO tabelaTarifiaria) {
        Iterator<CategoriaRequestDTO> iterator = tabelaTarifiaria.categorias().iterator();
        Integer inicio = 0;
        Integer fim = 0;
        Integer ordem = 0;

        while (iterator.hasNext()) {
            boolean isPrimeiro = true;
            var categoria = iterator.next();
            var categoriaNome = categoria.nome();
            var faixas = categoria.faixas();
            if (faixas.isEmpty()) throw new FaixaTarifariaException("Erro na criação da faixa tarifaria da tabela "
                    + tabelaTarifiaria.nome(), categoriaNome);

            for (var faixa : faixas) {
                if (isPrimeiro) {
                    inicio = faixa.inicio();
                    fim = faixa.fim();
                    ordem = faixa.ordem();
                    isPrimeiro = false;
                    continue;
                }

                if (faixa.inicio() <= inicio)
                    throw new FaixaTarifariaValidacaoCamposException("Erro no campo inicio da categoria ", categoriaNome);
                if (faixa.fim() <= fim)
                    throw new FaixaTarifariaValidacaoCamposException("Erro no campo fim da categoria ", categoriaNome);
                if (faixa.ordem() <= ordem)
                    throw new FaixaTarifariaValidacaoCamposException("Erro no campo ordem da categoria ", categoriaNome);

                inicio = faixa.inicio();
                fim = faixa.fim();
                ordem = faixa.ordem();

            }

        }

    }


    private static void validaCategoriaTabelaTarafia(TabelaTarifariaRequestDTO tabelaTarifiaria) {
        if (tabelaTarifiaria.categorias().size() < 4) {
            throw new TabelaTarifariaException("Erro tarefa", tabelaTarifiaria.nome());
        }

        Iterator<CategoriaRequestDTO> iterator = tabelaTarifiaria
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