package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.response;

import java.time.LocalDate;
import java.util.List;

public record TabelaTarifariaResponseDTO(
        Long id,
        boolean ativo,
        LocalDate dataVigencia,
        List<FaixaTarifariaResponseDTO> faixaTarifariaList
) {
}