package com.gitgub.diogenesssantos.api.acleanarquitecture.domain;

import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaValidacaoCamposException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FaixaTarifariaTest {

    @Test
    @DisplayName("Should create a FaixaTarifaria and expose its values, when all fields are valid.")
    void shouldCreateFaixaTarifariaAndExposeItsValues_WhenAllFieldsAreValid() {
        FaixaTarifaria faixa = new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, new BigDecimal("5.50"), 1);

        assertEquals(Categoria.COMERCIAL, faixa.getCategoria());
        assertEquals(0, faixa.getInicio().intValue());
        assertEquals(10, faixa.getFim().intValue());
        assertEquals(new BigDecimal("5.50"), faixa.getValorUnitario());
        assertEquals(1, faixa.getOrdem().intValue());
    }

    @Test
    @DisplayName("Should throw FaixaTarifariaValidacaoCamposException, when fim is equal to inicio.")
    void shouldThrowFaixaTarifariaValidacaoCamposException_WhenFimIsEqualToInicio() {
        assertThrows(FaixaTarifariaValidacaoCamposException.class,
                () -> new FaixaTarifaria(Categoria.COMERCIAL, 5, 5, new BigDecimal("1.00"), 1));
    }

    @Test
    @DisplayName("Should throw FaixaTarifariaValidacaoCamposException, when fim is less than inicio.")
    void shouldThrowFaixaTarifariaValidacaoCamposException_WhenFimIsLessThanInicio() {
        assertThrows(FaixaTarifariaValidacaoCamposException.class,
                () -> new FaixaTarifaria(Categoria.COMERCIAL, 10, 5, new BigDecimal("1.00"), 1));
    }

    @Test
    @DisplayName("Should throw FaixaTarifariaValidacaoCamposException, when valorUnitario is negative.")
    void shouldThrowFaixaTarifariaValidacaoCamposException_WhenValorUnitarioIsNegative() {
        assertThrows(FaixaTarifariaValidacaoCamposException.class,
                () -> new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, new BigDecimal("-0.01"), 1));
    }

    @Test
    @DisplayName("Should create a FaixaTarifaria, when valorUnitario is zero.")
    void shouldCreateFaixaTarifaria_WhenValorUnitarioIsZero() {
        FaixaTarifaria faixa = new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, BigDecimal.ZERO, 1);

        assertEquals(BigDecimal.ZERO, faixa.getValorUnitario());
    }

    @Test
    @DisplayName("Should throw NullPointerException, when categoria is null.")
    void shouldThrowNullPointerException_WhenCategoriaIsNull() {
        assertThrows(NullPointerException.class,
                () -> new FaixaTarifaria(null, 0, 10, new BigDecimal("1.00"), 1));
    }

    @Test
    @DisplayName("Should throw NullPointerException, when inicio is null.")
    void shouldThrowNullPointerException_WhenInicioIsNull() {
        assertThrows(NullPointerException.class,
                () -> new FaixaTarifaria(Categoria.COMERCIAL, null, 10, new BigDecimal("1.00"), 1));
    }

    @Test
    @DisplayName("Should throw NullPointerException, when fim is null.")
    void shouldThrowNullPointerException_WhenFimIsNull() {
        assertThrows(NullPointerException.class,
                () -> new FaixaTarifaria(Categoria.COMERCIAL, 0, null, new BigDecimal("1.00"), 1));
    }

    @Test
    @DisplayName("Should throw NullPointerException, when valorUnitario is null.")
    void shouldThrowNullPointerException_WhenValorUnitarioIsNull() {
        assertThrows(NullPointerException.class,
                () -> new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, null, 1));
    }

    @Test
    @DisplayName("Should throw NullPointerException, when ordem is null.")
    void shouldThrowNullPointerException_WhenOrdemIsNull() {
        assertThrows(NullPointerException.class,
                () -> new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, new BigDecimal("1.00"), null));
    }

    @Test
    @DisplayName("Should be equal and have the same hashCode, when two instances have the same values.")
    void shouldBeEqualAndHaveSameHashCode_WhenTwoInstancesHaveTheSameValues() {
        FaixaTarifaria primeira = new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, new BigDecimal("5.50"), 1);
        FaixaTarifaria segunda = new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, new BigDecimal("5.50"), 1);

        assertEquals(primeira, segunda);
        assertEquals(primeira.hashCode(), segunda.hashCode());
    }

    @Test
    @DisplayName("Should not be equal, when the values differ.")
    void shouldNotBeEqual_WhenTheValuesDiffer() {
        FaixaTarifaria original = new FaixaTarifaria(Categoria.COMERCIAL, 0, 10, new BigDecimal("5.50"), 1);
        FaixaTarifaria outraCategoria = new FaixaTarifaria(Categoria.INDUSTRIAL, 0, 10, new BigDecimal("5.50"), 1);
        FaixaTarifaria outroFim = new FaixaTarifaria(Categoria.COMERCIAL, 0, 20, new BigDecimal("5.50"), 1);

        assertNotEquals(original, outraCategoria);
        assertNotEquals(original, outroFim);
    }
}
