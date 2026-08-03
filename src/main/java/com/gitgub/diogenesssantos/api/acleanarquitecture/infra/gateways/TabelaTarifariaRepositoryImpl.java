package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.gateways;

import com.gitgub.diogenesssantos.api.acleanarquitecture.application.gateways.output.TabelaTarifariaRepository;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.TabelaTarifariaEntityRepository;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.TabelaTarifariaEntity;

public class TabelaTarifariaRepositoryImpl implements TabelaTarifariaRepository {
   private final TabelaTarifariaEntityRepository entityRepository;
   private final TabelaTarifariaMapper tabelaTarifariaMapper;

    public TabelaTarifariaRepositoryImpl(TabelaTarifariaEntityRepository entityRepository,
                                         TabelaTarifariaMapper tabelaTarifariaMapper) {
        this.entityRepository = entityRepository;
        this.tabelaTarifariaMapper = tabelaTarifariaMapper;
    }

    @Override
    public TabelaTarifaria salvar(TabelaTarifaria tabelaTarifaria) {
        TabelaTarifariaEntity tabelaTarifariaEntity = tabelaTarifariaMapper.modelToEntity(tabelaTarifaria);
        TabelaTarifariaEntity tabelaTarifariaEntitySave = entityRepository.save(tabelaTarifariaEntity);

        return tabelaTarifariaMapper.entityToModel(tabelaTarifariaEntitySave);
    }
}
