package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

public record FaixaTarifariaResponseDTO(
        Long id,
        String categoria,
        int inicio,
        int fim,
        int ordem) {}
