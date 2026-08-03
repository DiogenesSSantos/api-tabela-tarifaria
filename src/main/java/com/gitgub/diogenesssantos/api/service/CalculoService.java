package com.gitgub.diogenesssantos.api.service;

import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoRequestDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoResponseDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.DetalheFaixaResponseDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.FaixaResponseDTO;
import com.gitgub.diogenesssantos.api.acleanarquitecture.application.TabelaTarifariaNaoAtivaException;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
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
        validaExisteTabelaOuThrow();
        var categoria = calculoRequestDTO.categoria();
        var consumo = calculoRequestDTO.consumo();


        List<FaixaTarifaria> faixas = faixaRepo.findFaixasAtivasByCategoria(categoria);
        FaixaTarifaria ultimaFaixa = faixas.getLast();

        int restante = consumo;
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<DetalheFaixaResponseDTO> detalhamento = new ArrayList<>();

        for (FaixaTarifaria faixa : faixas) {
            if (restante <= 0) break;

            int faixaInicio = faixa.getInicio();
            int faixaFim = faixa.getFim();
            int faixaCapacidade = faixaFim - faixaInicio;
            int m3Cobrado = faixa.equals(ultimaFaixa) ? restante : Math.min(restante, faixaCapacidade);

            if (m3Cobrado > 0) {
                BigDecimal subtotal = faixa.getValorUnitario().multiply(BigDecimal.valueOf(m3Cobrado));
                valorTotal = valorTotal.add(subtotal);
                detalhamento.add(new DetalheFaixaResponseDTO(
                        new FaixaResponseDTO(faixaInicio, faixaFim),
                        m3Cobrado,
                        faixa.getValorUnitario(),
                        subtotal
                ));

                restante -= m3Cobrado;

            }
        }

        return new CalculoResponseDTO(categoria, consumo, valorTotal, detalhamento);

    }

    private void validaExisteTabelaOuThrow () {
        if (tabelaRepo.existsByAtivoTrue()) return;
        throw new TabelaTarifariaNaoAtivaException("Nenhuma tabela tarifaria ativa não banco de dados.");
    }

}
