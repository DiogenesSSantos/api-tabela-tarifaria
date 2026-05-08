package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

import java.time.LocalDate;
import java.util.List;

public record TabelaTarifariaRequestDTO(
        String nome,
        LocalDate dataVigencia,
        List<CategoriaRequestDTO> categorias
) {}