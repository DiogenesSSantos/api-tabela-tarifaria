package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria;

import java.util.List;

public record CategoriaRequest(
        String nome,
        List<FaixaRequest> faixas
) {}
