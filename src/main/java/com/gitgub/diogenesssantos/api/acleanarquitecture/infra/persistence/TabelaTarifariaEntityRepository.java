package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence;

import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model.TabelaTarifariaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabelaTarifariaEntityRepository extends JpaRepository<TabelaTarifariaEntity, Long> {

    boolean existsByAtivoTrue();

    @Query("""
                SELECT DISTINCT t FROM TabelaTarifariaEntity t
                JOIN FETCH t.faixaTarifariaList f
            """)
    List<TabelaTarifariaEntity> buscarTodas();

    @Query("""
            SELECT DISTINCT t FROM TabelaTarifariaEntity t
            JOIN FETCH t.faixaTarifariaList f
            WHERE t.ativo = TRUE
            ORDER BY t.dataVigencia DESC, f.ordem ASC
            """)
    List<TabelaTarifariaEntity> buscarTodasAtivas();

    @Query("""
                SELECT DISTINCT t FROM TabelaTarifariaEntity t
                JOIN FETCH t.faixaTarifariaList f 
                WHERE t.id=:id
            """)
    Optional<TabelaTarifariaEntity> buscarPorId(@Param("id") Long id);

}
