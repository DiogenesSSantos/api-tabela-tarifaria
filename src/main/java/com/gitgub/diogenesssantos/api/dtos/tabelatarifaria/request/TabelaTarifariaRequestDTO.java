package com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

public record TabelaTarifariaRequestDTO(
        @NotBlank(message = "Erro na tabela tarifaria, o nome não pode ser null ou ser vázia.")
        String nome,


        @NotNull(message = "Erro na tabela tarifaria, a data não pode ser null.")
        LocalDate dataVigencia,

        @Valid
        @NotNull
        @Size(min = 4, message = "Deve conter 4 categorias")
        List<CategoriaRequestDTO> categorias
) {}