package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

import java.time.LocalDate;
import java.util.List;

public record TabelaTarifariaResponseDTO(
        Long id,
        boolean ativo,
        LocalDate dataVigencia,
        List<FaixaTarifariaResponseDTO> faixaTarifariaList
) {
}