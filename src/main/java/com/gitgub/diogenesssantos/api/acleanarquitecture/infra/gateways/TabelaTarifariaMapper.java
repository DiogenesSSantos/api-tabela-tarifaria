package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.gateways;

import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.Categoria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.CategoriaEntity;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.FaixaTarifariaEntity;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.TabelaTarifariaEntity;


import java.util.ArrayList;
import java.util.List;

public class TabelaTarifariaMapper {


    public TabelaTarifariaEntity modelToEntity(TabelaTarifaria tabelaTarifaria) {
        return new TabelaTarifariaEntity(tabelaTarifaria.getNome(),
                tabelaTarifaria.getDataVigencia(),
                tabelaTarifaria.isAtivo(),
                listFaixaTarifariaModelToEntity(tabelaTarifaria.getFaixaTarifariaList()));
    }


    public TabelaTarifaria entityToModel(TabelaTarifariaEntity tabelaTarifariaEntity) {
        return new TabelaTarifaria(tabelaTarifariaEntity.getNome(),
                tabelaTarifariaEntity.getDataVigencia(),
                tabelaTarifariaEntity.isAtivo(),
                listFaixaTarifariaEntityToModel(tabelaTarifariaEntity.getFaixaTarifariaList()));


    }


    public List<FaixaTarifaria> listFaixaTarifariaEntityToModel(List<FaixaTarifariaEntity>
                                                                        faixaTarifariaEntityListList) {
        List<FaixaTarifaria> list = new ArrayList<>();

        for (FaixaTarifariaEntity faixaTarifaria : faixaTarifariaEntityListList) {
            FaixaTarifaria faixa = faixaTarifariaEntityToModel(faixaTarifaria);
            list.add(faixa);
        }
        return list;
    }

    private List<FaixaTarifariaEntity> listFaixaTarifariaModelToEntity(List<FaixaTarifaria>
                                                                               faixaTarifariaList) {
        List<FaixaTarifariaEntity> list = new ArrayList<>();

        for (FaixaTarifaria faixaTarifaria : faixaTarifariaList) {
            FaixaTarifariaEntity faixaEntity = faixaTarifariaModelToEntity(faixaTarifaria);
            list.add(faixaEntity);
        }
        return list;
    }

    private static FaixaTarifariaEntity faixaTarifariaModelToEntity(FaixaTarifaria faixaTarifaria) {
        return new FaixaTarifariaEntity(
                CategoriaEntity
                        .valueOf(faixaTarifaria.getCategoria().name()),
                faixaTarifaria.getInicio(),
                faixaTarifaria.getFim(),
                faixaTarifaria.getValorUnitario(),
                faixaTarifaria.getOrdem());
    }

    private static FaixaTarifaria faixaTarifariaEntityToModel(FaixaTarifariaEntity faixaTarifariaEntity) {
        return new FaixaTarifaria(
                Categoria.valueOf(faixaTarifariaEntity.getCategoria().name()),
                faixaTarifariaEntity.getInicio(),
                faixaTarifariaEntity.getFim(),
                faixaTarifariaEntity.getValorUnitario(),
                faixaTarifariaEntity.getOrdem());
    }
}

