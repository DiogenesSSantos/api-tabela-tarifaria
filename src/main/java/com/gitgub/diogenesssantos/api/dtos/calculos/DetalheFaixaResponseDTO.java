package com.gitgub.diogenesssantos.api.dtos.calculos;

import java.math.BigDecimal;

public record DetalheFaixaResponseDTO(FaixaResponseDTO faixa, Integer m3Cobrados, BigDecimal valorUnitario, BigDecimal subtotal) {}