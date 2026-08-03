package com.gitgub.diogenesssantos.api.acleanarquitecture.application.gateways.output;

import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.Categoria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.FaixaTarifaria;

import java.util.List;

public interface FaixaTarifariaRepository {

    List<FaixaTarifaria> buscarFaixasAtivasPorCategoria(Categoria categoria);

}
