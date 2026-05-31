package com.gitgub.diogenesssantos.api.repository;

import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TabelaTarifariaRepository extends JpaRepository<TabelaTarifaria, Long> {
    List<TabelaTarifaria> findByAtivoTrueOrderByDataVigenciaDesc();

    @Query("""
                SELECT t FROM TabelaTarifaria t
                JOIN FETCH t.faixaTarifariaList f
            """)
    List<TabelaTarifaria> buscarTodas();

}
