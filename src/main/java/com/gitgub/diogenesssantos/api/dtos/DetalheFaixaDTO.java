package com.gitgub.diogenesssantos.api.dtos;

import java.math.BigDecimal;

public record DetalheFaixaDTO(FaixaDTO faixa, Integer m3Cobrados, BigDecimal valorUnitario, BigDecimal subtotal) {}