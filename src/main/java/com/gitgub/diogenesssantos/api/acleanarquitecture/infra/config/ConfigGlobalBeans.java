package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.config;


import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.gateways.FaixaTarifariaRepositoryImpl;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.gateways.TabelaTarifariaMapper;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.gateways.TabelaTarifariaRepositoryImpl;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.FaixaTarifariaEntityRepository;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.TabelaTarifariaEntityRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigGlobalBeans {


    @Bean
    public TabelaTarifariaMapper mapper() {
       return new TabelaTarifariaMapper();
    }

    @Bean
    public TabelaTarifariaRepositoryImpl tabelaTarifariaRepositoryImpl(TabelaTarifariaEntityRepository entityRepository,
                                                                      TabelaTarifariaMapper mapper) {
        return new TabelaTarifariaRepositoryImpl(entityRepository, mapper);
    }

    @Bean
    public FaixaTarifariaRepositoryImpl faixaTarifariaRepositoryImpl(FaixaTarifariaEntityRepository entityRepository,
                                                                     TabelaTarifariaMapper mapper) {
        return new FaixaTarifariaRepositoryImpl(entityRepository, mapper);
    }


}
