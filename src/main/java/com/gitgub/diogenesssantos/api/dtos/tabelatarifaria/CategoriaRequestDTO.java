package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

import java.util.List;

public record CategoriaRequestDTO(
        String nome,
        List<FaixaRequestDTO> faixas
) {}
