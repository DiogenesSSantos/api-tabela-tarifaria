package com.gitgub.diogenesssantos.api.exceptiohandler;


import com.gitgub.diogenesssantos.api.exception.CategoriaNomeException;
import com.gitgub.diogenesssantos.api.exception.CategoriaNomeInvalidoException;
import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaException;
import com.gitgub.diogenesssantos.api.exception.TabelaTarifariaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(TabelaTarifariaException.class)
    public ResponseEntity<Error> tabelaTarifariaException(TabelaTarifariaException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                String.format("A tarifa deve conter todas categorias (COMERCIAL,INDUSTRIAL PARTICULAR, PUBLICO)."),
                TabelaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


    @ExceptionHandler(CategoriaNomeException.class)
    public ResponseEntity<Error> categoriaNomeException(CategoriaNomeException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "Categoria não pode ser null, ou ter um nome em branco.",
                TabelaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


    @ExceptionHandler(CategoriaNomeInvalidoException.class)
    public ResponseEntity<Error> categoriaNomeInvalidoException(CategoriaNomeInvalidoException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                String.format("Nome %s incompatível as Categorias (COMERCIAL,INDUSTRIAL PARTICULAR, PUBLICO) ",
                        ex.getNomeCategoria()),
                TabelaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }

    @ExceptionHandler(FaixaTarifariaException.class)
    public ResponseEntity<Error> faixaTarifariaException(FaixaTarifariaException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                String.format("A categoria %s deve ter pelos menos uma faixa tarifaria.", ex.getNomeCategoria()),
                TabelaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


}
