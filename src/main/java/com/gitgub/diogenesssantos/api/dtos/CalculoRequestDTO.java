package com.gitgub.diogenesssantos.api.dtos;

import com.gitgub.diogenesssantos.api.model.Categoria;

public record CalculoRequestDTO(Categoria categoria, Integer consumo) {}
