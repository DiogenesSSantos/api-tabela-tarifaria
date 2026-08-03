package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence;

import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.CategoriaEntity;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.FaixaTarifariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaixaTarifariaEntityRepository extends JpaRepository<FaixaTarifariaEntity, Long> {
    @Query("""
                SELECT DISTINCT f FROM FaixaTarifariaEntity f
                JOIN FETCH f.tabela t
                WHERE t.ativo = true
                  AND f.categoria = :categoria
                ORDER BY t.dataVigencia DESC, f.ordem ASC
            """)
    List<FaixaTarifariaEntity> findFaixasAtivasByCategoria(@Param("categoria") CategoriaEntity categoria);
}
