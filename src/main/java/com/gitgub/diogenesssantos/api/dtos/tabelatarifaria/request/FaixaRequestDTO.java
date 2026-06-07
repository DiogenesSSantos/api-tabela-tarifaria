package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.request;

public record FaixaRequestDTO(
        Integer inicio,
        Integer fim,
        Double valorUnitario,
        Integer ordem
) {}