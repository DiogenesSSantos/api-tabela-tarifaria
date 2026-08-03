package com.gitgub.diogenesssantos.api.acleanarquitecture.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CategoriaTest {

    @Test
    @DisplayName("Should contain exactly the four expected categories.")
    void shouldContainExactlyTheFourExpectedCategories() {
        assertArrayEquals(
                new Categoria[]{Categoria.COMERCIAL, Categoria.INDUSTRIAL, Categoria.PARTICULAR, Categoria.PUBLICO},
                Categoria.values());
    }

    @Test
    @DisplayName("Should return the enum constant, when valueOf receives a valid name.")
    void shouldReturnEnumConstant_WhenValueOfReceivesAValidName() {
        assertEquals(Categoria.COMERCIAL, Categoria.valueOf("COMERCIAL"));
        assertEquals(Categoria.INDUSTRIAL, Categoria.valueOf("INDUSTRIAL"));
        assertEquals(Categoria.PARTICULAR, Categoria.valueOf("PARTICULAR"));
        assertEquals(Categoria.PUBLICO, Categoria.valueOf("PUBLICO"));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException, when valueOf receives an invalid name.")
    void shouldThrowIllegalArgumentException_WhenValueOfReceivesAnInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> Categoria.valueOf("INVALIDA"));
    }
}
