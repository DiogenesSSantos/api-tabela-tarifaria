package com.gitgub.diogenesssantos.api.acleanarquitecture.infra;

import com.gitgub.diogenesssantos.api.acleanarquitecture.application.gateways.output.TabelaTarifariaRepository;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.Categoria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.FaixaTarifaria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.domain.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.TabelaTarifariaEntityRepository;
import com.gitgub.diogenesssantos.api.config.TestcontainersConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ContextConfiguration(initializers = TestcontainersConfiguration.Initializer.class)
@SpringBootTest
class TabelaTarifariaGatewayTest {

    @Autowired
    private TabelaTarifariaRepository tabelaTarifariaRepository;

    @Autowired
    private TabelaTarifariaEntityRepository tabelaTarifariaEntityRepository;

    @Test
    @DisplayName("Deve salvar uma tabela tarifária válida através do novo gateway de infra")
    void deveSalvarTabelaViaGateway() {
        var faixa1 = new FaixaTarifaria(Categoria.COMERCIAL, 0, 100, new BigDecimal("2.50"), 1);
        var faixa2 = new FaixaTarifaria(Categoria.COMERCIAL, 101, 300, new BigDecimal("2.20"), 2);
        var tabela = new TabelaTarifaria("Tabela Teste Gateway", LocalDate.of(2026, 1, 1), true,
                List.of(faixa1, faixa2));

        var tabelaSalva = tabelaTarifariaRepository.salvar(tabela);

        assertNotNull(tabelaSalva);
        assertNotNull(tabelaSalva.getFaixaTarifariaList());
        assertEquals(2, tabelaSalva.getFaixaTarifariaList().size());
        assertEquals("Tabela Teste Gateway", tabelaSalva.getNome());
    }

    @Test
    @DisplayName("Deve buscar tabelas ativas através do repositório de entidades novo")
    void deveBuscarTabelasAtivasViaRepositorioNovo() {
        var tabelasAtivas = tabelaTarifariaEntityRepository.buscarTodasAtivas();

        assertFalse(tabelasAtivas.isEmpty());
        assertTrue(tabelasAtivas.stream().allMatch(t -> t.isAtivo()));
        assertTrue(tabelasAtivas.stream().allMatch(t -> t.getFaixaTarifariaList() != null));
    }

    @Test
    @DisplayName("Deve buscar uma tabela por id com as faixas carregadas através do repositório de entidades novo")
    void deveBuscarTabelaPorIdComFaixasViaRepositorioNovo() {
        var tabela = tabelaTarifariaEntityRepository.buscarPorId(1L);

        assertTrue(tabela.isPresent());
        assertNotNull(tabela.get().getFaixaTarifariaList());
        assertFalse(tabela.get().getFaixaTarifariaList().isEmpty());
    }
}
