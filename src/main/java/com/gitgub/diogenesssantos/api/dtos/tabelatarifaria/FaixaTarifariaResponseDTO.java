package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

import com.fasterxml.jackson.annotation.JsonIgnore;

public record FaixaTarifariaResponseDTO(
        @JsonIgnore
        Long id,
        String categoria,
        int inicio,
        int fim,
        int ordem) {}
