package com.gitgub.diogenesssantos.api.assemble;

import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.FaixaTarifariaResponseDTO;
import com.gitgub.diogenesssantos.api.dtos.tabelatarifaria.TabelaTarifariaResponseDTO;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;

import java.util.List;

public class AssembleTabelaTarifaria {


    public static TabelaTarifariaResponseDTO modelToDTO(TabelaTarifaria tabelaTarifaria) {
        return new TabelaTarifariaResponseDTO(tabelaTarifaria.getId(),
                tabelaTarifaria.isAtivo(),
                tabelaTarifaria.getDataVigencia(),
                convertFaixaTarifariaToDTO(tabelaTarifaria.getFaixaTarifariaList()));

    }

    private static List<FaixaTarifariaResponseDTO> convertFaixaTarifariaToDTO(
            List<FaixaTarifaria> faixaTarifariaList) {
        return faixaTarifariaList.stream()
                .map(faixaTarifaria -> new FaixaTarifariaResponseDTO(faixaTarifaria.getId(),
                        faixaTarifaria.getCategoria().name(),
                        faixaTarifaria.getInicio(),
                        faixaTarifaria.getFim(),
                        faixaTarifaria.getOrdem()))
                .toList();

    }

    public static List<TabelaTarifariaResponseDTO> listModelToLisDTO(List<TabelaTarifaria> tabelaTarifariaListBD) {
        return tabelaTarifariaListBD.stream()
                .map(AssembleTabelaTarifaria::modelToDTO)
                .toList();
    }
}
