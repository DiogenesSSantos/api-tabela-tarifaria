package com.gitgub.diogenesssantos.api.repository;

import com.gitgub.diogenesssantos.api.model.Categoria;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaixaTarifariaRepository extends JpaRepository<FaixaTarifaria, Long> {
    @Query("""
                SELECT f FROM FaixaTarifaria f
                JOIN FETCH f.tabela t
                WHERE t.ativo = true
                  AND f.categoria = :categoria
                ORDER BY t.dataVigencia DESC, f.ordem ASC
            """)
    List<FaixaTarifaria> findFaixasAtivasByCategoria(@Param("categoria") Categoria categoria);
}
