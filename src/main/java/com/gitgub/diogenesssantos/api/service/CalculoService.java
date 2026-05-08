package com.gitgub.diogenesssantos.api.service;

import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoResponseDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.DetalheFaixaDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.FaixaDTO;
import com.gitgub.diogenesssantos.api.exception.CalculoRequestException;
import com.gitgub.diogenesssantos.api.exception.FaixaNaoCobreConsumoException;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.repository.FaixaTarifariaRepository;
import com.gitgub.diogenesssantos.api.repository.TabelaTarifariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalculoService {

    private final TabelaTarifariaRepository tabelaRepo;
    private final FaixaTarifariaRepository faixaRepo;

    public CalculoService(TabelaTarifariaRepository tabelaRepo, FaixaTarifariaRepository faixaRepo) {
        this.tabelaRepo = tabelaRepo;
        this.faixaRepo = faixaRepo;
    }

    @Transactional
    public CalculoResponseDTO calcular(CalculoRequestDTO calculoRequestDTO) {
        validaCalculoRequest(calculoRequestDTO);
        var categoria = calculoRequestDTO.categoria();
        var consumo = calculoRequestDTO.consumo();

        TabelaTarifaria tabela = tabelaRepo.findByAtivoTrueOrderByDataVigenciaDesc()
                .stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("Nenhuma tabela ativa"));

        List<FaixaTarifaria> faixas = faixaRepo.findByTabelaAndCategoriaOrderByOrdemAsc(tabela, categoria);

        int restante = consumo;
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<DetalheFaixaDTO> detalhamento = new ArrayList<>();

        for (FaixaTarifaria f : faixas) {
            if (restante <= 0) break;

            int faixaInicio = f.getInicio();
            int faixaFim = f.getFim();

            // capacidade máxima da faixa
            int faixaCapacidade = faixaFim - faixaInicio;

            // quanto realmente será cobrado nesta faixa
            int m3Cobrado = Math.min(restante, faixaCapacidade);

            if (m3Cobrado > 0) {
                BigDecimal subtotal = f.getValorUnitario().multiply(BigDecimal.valueOf(m3Cobrado));
                valorTotal = valorTotal.add(subtotal);

                detalhamento.add(new DetalheFaixaDTO(
                        new FaixaDTO(faixaInicio, faixaFim),
                        m3Cobrado,
                        f.getValorUnitario(),
                        subtotal
                ));

                restante -= m3Cobrado;
            }
        }

        if (restante > 0) {
            throw new FaixaNaoCobreConsumoException("Faixas não cobrem o consumo %d informado", categoria.name(),
                    consumo);
        }

        return new CalculoResponseDTO(categoria, consumo, valorTotal, detalhamento);
    }

    private void validaCalculoRequest(CalculoRequestDTO calculoRequestDTO) {
        if (calculoRequestDTO == null || calculoRequestDTO.consumo() == null || calculoRequestDTO.categoria() == null) {
            throw new CalculoRequestException("Erro no corpo JSON");
        }
    }

}
