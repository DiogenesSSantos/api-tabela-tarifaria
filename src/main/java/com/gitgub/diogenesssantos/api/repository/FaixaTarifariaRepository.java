package com.gitgub.diogenesssantos.api.repository;

import com.gitgub.diogenesssantos.api.model.Categoria;
import com.gitgub.diogenesssantos.api.model.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaixaTarifariaRepository extends JpaRepository<FaixaTarifaria, Long> {
    List<FaixaTarifaria> findByTabelaAndCategoriaOrderByOrdemAsc(TabelaTarifaria tabela, Categoria categoria);
}
