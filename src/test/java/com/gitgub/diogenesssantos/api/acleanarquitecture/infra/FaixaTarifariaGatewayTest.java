package com.gitgub.diogenesssantos.api.acleanarquitecture.infra;

import com.gitgub.diogenesssantos.api.acleanarquitecture.application.gateways.output.FaixaTarifariaRepository;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.Categoria;
import com.gitgub.diogenesssantos.api.config.TestcontainersConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(initializers = TestcontainersConfiguration.Initializer.class)
@SpringBootTest
class FaixaTarifariaGatewayTest {

    @Autowired
    private FaixaTarifariaRepository faixaTarifariaRepository;

    @Test
    @DisplayName("Deve buscar faixas ativas por categoria através do novo gateway de infra")
    void deveBuscarFaixasAtivasPorCategoriaViaGateway() {
        var faixas = faixaTarifariaRepository.buscarFaixasAtivasPorCategoria(Categoria.COMERCIAL);

        assertFalse(faixas.isEmpty());
        assertTrue(faixas.stream().allMatch(f -> f.getCategoria() == Categoria.COMERCIAL));
    }
}
