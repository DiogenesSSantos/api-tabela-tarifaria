package com.gitgub.diogenesssantos.api.dtos.calculos;

import com.gitgub.diogenesssantos.api.model.Categoria;
import com.gitgub.diogenesssantos.api.validation.CategoriaBeanValidation;
import jakarta.validation.constraints.NotNull;

public record CalculoRequestDTO(

        @NotNull(message = "Categoria inválida. Valores aceitos: COMERCIAL, INDUSTRIAL, PARTICULAR, PUBLICO")
        Categoria categoria,

        @NotNull(message = "Consumo inválido. Não pode null." )
        Integer consumo) {}
