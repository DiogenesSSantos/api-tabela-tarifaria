package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

import java.time.LocalDate;
import java.util.List;

public record TabelaTarifariaRequest(
        String nome,
        LocalDate dataVigencia,
        List<CategoriaRequest> categorias
) {}