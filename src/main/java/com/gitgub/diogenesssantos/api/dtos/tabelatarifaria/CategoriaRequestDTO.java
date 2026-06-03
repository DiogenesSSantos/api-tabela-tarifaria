package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

import com.gitgub.diogenesssantos.api.validation.CategoriaBeanValidation;

import java.util.List;

public record CategoriaRequestDTO(
        @CategoriaBeanValidation
        String nome,
        List<FaixaRequestDTO> faixas
) {}
