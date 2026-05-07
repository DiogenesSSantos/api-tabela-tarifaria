package com.gitgub.diogenesssantos.api.service;

import com.gitgub.diogenesssantos.api.dtos.calculos.CalculoResponseDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.DetalheFaixaDTO;
import com.gitgub.diogenesssantos.api.dtos.calculos.FaixaDTO;
import com.gitgub.diogenesssantos.api.model.Categoria;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.repository.FaixaTarifariaRepository;
import com.gitgub.diogenesssantos.api.repository.TabelaTarifariaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;

@Service
public class CalculoService {
    private final TabelaTarifariaRepository tabelaRepo;
    private final FaixaTarifariaRepository faixaRepo;

    public CalculoService(TabelaTarifariaRepository tabelaRepo, FaixaTarifariaRepository faixaRepo) {
        this.tabelaRepo = tabelaRepo;
        this.faixaRepo = faixaRepo;
    }

    @Transactional()
    public CalculoResponseDTO calcular(Categoria categoria, int consumo) {
        TabelaTarifaria tabela = tabelaRepo.findByAtivoTrueOrderByDataVigenciaDesc()
                .stream().findFirst().orElseThrow(() -> new IllegalStateException("Nenhuma tabela ativa"));

        List<FaixaTarifaria> faixas = faixaRepo.findByTabelaAndCategoriaOrderByOrdemAsc(tabela, categoria);
        validarCoberturaEConsistencia(faixas);


        int restante = consumo;
        BigDecimal valorTotal = BigDecimal.ZERO;
        List<DetalheFaixaDTO> detalhamento = new ArrayList<>();

        for (FaixaTarifaria faixa : faixas) {
            if (restante <= 0) break;

            int faixaInicio = faixa.getInicio();
            int faixaFim = faixa.getFim();
            int capacidade = faixaFim - faixaInicio + 1; // se fim inclusive

            // calcular quantos m3 desta faixa são cobrados
            int m3NaFaixa;
            if (consumo <= faixaInicio) {
                m3NaFaixa = 0;

            } else {
                int maxCobrado = Math.min(restante, Math.max(0, faixaFim - Math.max(faixaInicio, consumo - restante) + 1));
                // simplificar: calcular por limites
                int faixaDisponivel = Math.min(restante, Math.max(0, faixaFim - Math.max(faixaInicio, 0) + 1));
                // implementação robusta abaixo:
                int inicioCobrado = Math.max(faixaInicio, consumo - restante + 1); // não estritamente necessário
                // use abordagem direta:
                int inicioAplicavel = Math.max(faixaInicio, 0);
                int fimAplicavel = faixaFim;
                int cobrados = Math.min(restante, Math.max(0, fimAplicavel - inicioAplicavel + 1));
                m3NaFaixa = Math.min(restante, Math.max(0, Math.min(faixaFim, consumo) - faixaInicio + 1));
            }

            int m3Cobrado = Math.min(restante, Math.max(0, Math.min(faixa.getFim(), consumo) - faixa.getInicio() + 1));

            if (m3Cobrado <= 0) continue;
            BigDecimal subtotal = faixa.getValorUnitario().multiply(BigDecimal.valueOf(m3Cobrado));
            valorTotal = valorTotal.add(subtotal);
            detalhamento.add(new DetalheFaixaDTO(new FaixaDTO(faixa.getInicio(), faixa.getFim()), m3Cobrado, faixa.getValorUnitario(), subtotal));
            restante -= m3Cobrado;
        }
        if (restante > 0) throw new IllegalStateException("Faixas não cobrem o consumo informado");
        return new CalculoResponseDTO(categoria, consumo, valorTotal, detalhamento);
    }

    private void validarCoberturaEConsistencia(List<FaixaTarifaria> faixas) {
        if (faixas.isEmpty()) throw new IllegalStateException("Sem faixas para a categoria");

        OptionalInt minInicio = faixas.stream()
                .mapToInt(FaixaTarifaria::getInicio)
                .min();

        if (minInicio.isEmpty() || minInicio.getAsInt() != 0) throw new IllegalStateException("Faixas devem iniciar em 0");

        faixas.sort(Comparator.comparingInt(FaixaTarifaria::getOrdem));

        int lastFim = -1;
        for (FaixaTarifaria f : faixas) {

            if (f.getInicio() >= f.getFim()) throw new IllegalStateException("Inicio deve ser menor que fim");

            if (lastFim >= f.getInicio()) throw new IllegalStateException("Faixas sobrepostas ou mal ordenadas");

            lastFim = f.getFim();
        }
    }
}