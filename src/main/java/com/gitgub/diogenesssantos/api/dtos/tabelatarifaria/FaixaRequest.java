package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

public record FaixaRequest(
        Integer inicio,
        Integer fim,
        Double valorUnitario,
        Integer ordem
) {}