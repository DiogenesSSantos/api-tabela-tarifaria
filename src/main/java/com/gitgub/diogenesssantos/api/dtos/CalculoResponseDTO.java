package com.gitgub.diogenesssantos.api.dtos;

import com.gitgub.diogenesssantos.api.model.Categoria;

import java.math.BigDecimal;
import java.util.List;

public record CalculoResponseDTO(Categoria categoria, Integer consumoTotal,
                                 BigDecimal valorTotal, List<DetalheFaixaDTO> detalhamento) {
}
