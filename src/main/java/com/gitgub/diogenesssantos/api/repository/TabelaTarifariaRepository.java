package com.gitgub.diogenesssantos.api.repository;

import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TabelaTarifariaRepository extends JpaRepository<TabelaTarifaria, Long> {

    boolean existsByAtivoTrue();

    @Query("""
                SELECT t FROM TabelaTarifaria t
                JOIN FETCH t.faixaTarifariaList f
            """)
    List<TabelaTarifaria> buscarTodas();

    @Query("""
            SELECT t FROM TabelaTarifaria t
            JOIN FETCH t.faixaTarifariaList f
            WHERE t.ativo = TRUE
            ORDER BY t.dataVigencia DESC, f.ordem ASC
            """)
    List<TabelaTarifaria> buscarTodasAtivas();

    @Query("""
                SELECT t FROM TabelaTarifaria t
                JOIN FETCH t.faixaTarifariaList f 
                WHERE t.id=:id
            """)
    Optional<TabelaTarifaria> buscarPorId(@Param("id") Long id);

}
