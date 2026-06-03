package com.gitgub.diogenesssantos.api.service;


import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.CategoriaRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.FaixaRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaRequestDTO;
import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaException;
import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaValidacaoCamposException;
import com.gitgub.diogenesssantos.api.model.Categoria;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.repository.FaixaTarifariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class FaixaTarifariaService {
    private final FaixaTarifariaRepository faixaRepo;


    public FaixaTarifariaService(FaixaTarifariaRepository faixaRepo) {
        this.faixaRepo = faixaRepo;
    }

    @Transactional
    public void salvarTodasFaixas(List<FaixaTarifaria> faixaTarifariaList) {
        faixaRepo.saveAll(faixaTarifariaList);
    }



    List<FaixaTarifaria> criarFaixaTarifariaList(TabelaTarifariaRequestDTO tabelaTarifaria, TabelaTarifaria tabela) {
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



    public void validaFaixasTabelaTarifaria(TabelaTarifariaRequestDTO tabelaTarifaria) {

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

}
