package com.gitgub.diogenesssantos.api.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CategoriaValidaValidator.class)
public @interface CategoriaBeanValidation {

    String message() default "Categoria inválida. Valores aceitos: COMERCIAL, INDUSTRIAL, PARTICULAR, PUBLICO";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


class CategoriaValidaValidator implements ConstraintValidator<CategoriaBeanValidation, String> {

    private static final Set<String> CATEGORIAS_VALIDAS = Set.of(
            "COMERCIAL", "INDUSTRIAL", "PARTICULAR", "PUBLICO"
    );


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return false;
        return CATEGORIAS_VALIDAS.contains(value.toUpperCase());
    };
}
