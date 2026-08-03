package com.gitgub.diogenesssantos.api.acleanarquitecture.domain;

import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaException;
import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaValidacaoCamposException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TabelaTarifariaTest {

    private FaixaTarifaria faixa(int inicio, int fim, int ordem) {
        return new FaixaTarifaria(Categoria.COMERCIAL, inicio, fim, new BigDecimal("5.00"), ordem);
    }

    @Test
    @DisplayName("Should create a TabelaTarifaria and expose its values, when the faixas sequence is valid.")
    void shouldCreateTabelaTarifariaAndExposeItsValues_WhenTheFaixasSequenceIsValid() {
        LocalDate dataVigencia = LocalDate.of(2026, 8, 1);
        List<FaixaTarifaria> faixas = List.of(faixa(0, 5, 1), faixa(6, 10, 2));

        TabelaTarifaria tabela = new TabelaTarifaria("Tabela Teste", dataVigencia, true, faixas);

        assertEquals("Tabela Teste", tabela.getNome());
        assertEquals(dataVigencia, tabela.getDataVigencia());
        assertTrue(tabela.isAtivo());
        assertEquals(2, tabela.getFaixaTarifariaList().size());
    }

    @Test
    @DisplayName("Should keep ativo false, when the constructor receives ativo false.")
    void shouldKeepAtivoFalse_WhenTheConstructorReceivesAtivoFalse() {
        TabelaTarifaria tabela = new TabelaTarifaria(
                "Tabela Teste", LocalDate.now(), false, List.of(faixa(0, 5, 1), faixa(6, 10, 2)));

        assertFalse(tabela.isAtivo());
    }

    @Test
    @DisplayName("Should throw FaixaTarifariaException, when the faixas list is empty.")
    void shouldThrowFaixaTarifariaException_WhenTheFaixasListIsEmpty() {
        assertThrows(FaixaTarifariaException.class,
                () -> new TabelaTarifaria("Tabela Teste", LocalDate.now(), true, List.of()));
    }

    @Test
    @DisplayName("Should throw NullPointerException, when the faixas list is null.")
    void shouldThrowNullPointerException_WhenTheFaixasListIsNull() {
        assertThrows(NullPointerException.class,
                () -> new TabelaTarifaria("Tabela Teste", LocalDate.now(), true, null));
    }

    @Test
    @DisplayName("Should throw FaixaTarifariaValidacaoCamposException, when the first faixa does not start at zero.")
    void shouldThrowFaixaTarifariaValidacaoCamposException_WhenTheFirstFaixaDoesNotStartAtZero() {
        assertThrows(FaixaTarifariaValidacaoCamposException.class,
                () -> new TabelaTarifaria("Tabela Teste", LocalDate.now(), true,
                        List.of(faixa(1, 5, 1), faixa(6, 10, 2))));
    }

    @Test
    @DisplayName("Should throw FaixaTarifariaValidacaoCamposException, when the next faixa does not start right after the previous fim.")
    void shouldThrowFaixaTarifariaValidacaoCamposException_WhenTheNextFaixaDoesNotStartRightAfterThePreviousFim() {
        assertThrows(FaixaTarifariaValidacaoCamposException.class,
                () -> new TabelaTarifaria("Tabela Teste", LocalDate.now(), true,
                        List.of(faixa(0, 5, 1), faixa(7, 10, 2))));
    }

    @Test
    @DisplayName("Should throw FaixaTarifariaValidacaoCamposException, when the ordem is not strictly increasing.")
    void shouldThrowFaixaTarifariaValidacaoCamposException_WhenTheOrdemIsNotStrictlyIncreasing() {
        assertThrows(FaixaTarifariaValidacaoCamposException.class,
                () -> new TabelaTarifaria("Tabela Teste", LocalDate.now(), true,
                        List.of(faixa(0, 5, 2), faixa(6, 10, 1))));
    }

    @Test
    @DisplayName("Should be equal, when two instances have the same values.")
    void shouldBeEqual_WhenTwoInstancesHaveTheSameValues() {
        TabelaTarifaria primeira = new TabelaTarifaria(
                "Tabela Teste", LocalDate.of(2026, 8, 1), true, List.of(faixa(0, 5, 1), faixa(6, 10, 2)));
        TabelaTarifaria segunda = new TabelaTarifaria(
                "Tabela Teste", LocalDate.of(2026, 8, 1), true, List.of(faixa(0, 5, 1), faixa(6, 10, 2)));

        assertEquals(primeira, segunda);
    }
}
