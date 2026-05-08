package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

public record FaixaRequestDTO(
        Integer inicio,
        Integer fim,
        Double valorUnitario,
        Integer ordem
) {}