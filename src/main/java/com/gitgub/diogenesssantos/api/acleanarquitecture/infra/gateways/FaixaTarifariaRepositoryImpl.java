package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.gateways;

import com.gitgub.diogenesssantos.api.acleanarquitecture.application.gateways.output.FaixaTarifariaRepository;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.Categoria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.FaixaTarifariaEntityRepository;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.CategoriaEntity;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.FaixaTarifariaEntity;

import java.util.List;

public class FaixaTarifariaRepositoryImpl implements FaixaTarifariaRepository {

    private final FaixaTarifariaEntityRepository entityRepository;
    private final TabelaTarifariaMapper tabelaTarifariaMapper;

    public FaixaTarifariaRepositoryImpl(FaixaTarifariaEntityRepository entityRepository,
                                        TabelaTarifariaMapper tabelaTarifariaMapper) {
        this.entityRepository = entityRepository;
        this.tabelaTarifariaMapper = tabelaTarifariaMapper;
    }

    @Override
    public List<FaixaTarifaria> buscarFaixasAtivasPorCategoria(Categoria categoria) {
        List<FaixaTarifariaEntity> faixasAtivas = entityRepository
                .findFaixasAtivasByCategoria(CategoriaEntity.valueOf(categoria.name()));

        return tabelaTarifariaMapper.listFaixaTarifariaEntityToModel(faixasAtivas);
    }
}
