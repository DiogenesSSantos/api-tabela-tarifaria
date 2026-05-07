package com.gitgub.diogenesssantos.api.service;

import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.CategoriaRequest;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.FaixaRequest;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequest;
import com.gitgub.diogenesssantos.api.exception.CategoriaNomeException;
import com.gitgub.diogenesssantos.api.exception.CategoriaNomeInvalidoException;
import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaException;
import com.gitgub.diogenesssantos.api.exception.TabelaTarifariaException;
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
    public TabelaTarifaria salvarTabela(TabelaTarifariaRequest tabelaTarifiaria) {
        validaTabelaTarifariaRequest(tabelaTarifiaria);

        TabelaTarifaria tabela = new TabelaTarifaria();
        tabela.setNome(tabelaTarifiaria
                .nome());
        tabela.setDataVigencia(tabelaTarifiaria
                .dataVigencia());
        tabela.setAtivo(true);
        tabelaRepo.save(tabela);

        for (CategoriaRequest catReq : tabelaTarifiaria
                .categorias()) {

            Categoria categoria = Categoria.valueOf(catReq.nome().toUpperCase());

            for (FaixaRequest faixaReq : catReq.faixas()) {
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


    public List<TabelaTarifaria> listarTabelas() {
        return tabelaRepo.findAll();

    }

    public List<TabelaTarifaria> salvarTabelaEmLote(List<TabelaTarifariaRequest> tabelaTarifariaRequests) {
        List<TabelaTarifaria> tabelaTarifariaList = new ArrayList<>();

        Iterator<TabelaTarifariaRequest> iterator = tabelaTarifariaRequests.iterator();
        while (iterator.hasNext()) {
            var tabelaTarifaria = iterator.next();
            var tabelaTarSalvaBD = salvarTabela(tabelaTarifaria);

            tabelaTarifariaList.add(tabelaTarSalvaBD);
        }

        return tabelaTarifariaList;

    }


    private void validaTabelaTarifariaRequest(TabelaTarifariaRequest tabelaTarifiaria) {
        validaCategoriaTabelaTarafia(tabelaTarifiaria);
        validaFaixasTabelaTarifaria(tabelaTarifiaria);
    }

    private void validaFaixasTabelaTarifaria(TabelaTarifariaRequest tabelaTarifiaria) {
        Iterator<CategoriaRequest> iterator = tabelaTarifiaria.categorias().iterator();
        while (iterator.hasNext()) {
            var categoria = iterator.next();
            var categoriaNome = categoria.nome();
            var faixas = categoria.faixas();
            if (faixas.isEmpty()) throw new FaixaTarifariaException("Obrigatória conter uma faixa tarifaria." ,
                    categoriaNome);

        }

    }


    private static void validaCategoriaTabelaTarafia(TabelaTarifariaRequest tabelaTarifiaria) {
        if (tabelaTarifiaria.categorias().size() < 4) {
            throw new TabelaTarifariaException("A tabelaTarifaria tabelaTarifiaria" +
                    "uer todas categorias de cobrança.");
        }

        Iterator<CategoriaRequest> iterator = tabelaTarifiaria
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


}