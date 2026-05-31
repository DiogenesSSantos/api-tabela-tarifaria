package com.gitgub.diogenesssantos.api;

import com.gitgub.diogenesssantos.api.config.TestcontainersConfiguration;
import com.gitgub.diogenesssantos.api.model.TabelaTarifaria;
import com.gitgub.diogenesssantos.api.repository.TabelaTarifariaRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(initializers = TestcontainersConfiguration.Initializer.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TabelaTarifariaRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private TabelaTarifariaRepository tabelaTarifariaRepository;


	@Test
    @DisplayName("Should find all, when callable the method buscarTodos.")
	void shouldFindAll_WhenCallableTheMethodBuscarTodos() {
        List<TabelaTarifaria> tabelaTarifaria = tabelaTarifariaRepository.buscarTodas();
        assertNotNull(tabelaTarifaria);
    }



    @Test
    @DisplayName("Should inactive TabelaTarifaria," +
            "when setAtivo assign a value false and to the find again TabelaTarifa o atributte isAtivo return false.")
    void shouldInactiveTabelaTarifaria_WhenSetAtivoAssignAValuefalse_And_ToTheFindAgainTheTabelaTarifaTheAtributte() {
        var tabelaTarifariaBD = tabelaTarifariaRepository.findById(1L).get();
        tabelaTarifariaBD.setAtivo(false);
        tabelaTarifariaRepository.saveAndFlush(tabelaTarifariaBD);

        entityManager.clear();
        var tabelaTarifariaAtualizada = tabelaTarifariaRepository.findById(1L).get();

        assertFalse(tabelaTarifariaAtualizada.isAtivo());


    }

}
