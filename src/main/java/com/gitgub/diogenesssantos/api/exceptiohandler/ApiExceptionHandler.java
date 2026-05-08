package com.gitgub.diogenesssantos.api.exceptiohandler;


import com.gitgub.diogenesssantos.api.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(TabelaTarifariaException.class)
    public ResponseEntity<Error> tabelaTarifariaException(TabelaTarifariaException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                String.format("Erro na criação de tabela tarifaria de nome %s.", ex.getNomeTabela()),
                String.format("A tabela tarifaria deve conter todas categorias (COMERCIAL,INDUSTRIAL PARTICULAR, PUBLICO)."),
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
                CategoriaNomeException.class.getSimpleName(),
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
                CategoriaNomeInvalidoException.class.getSimpleName(),
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
                FaixaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


    @ExceptionHandler(FaixaTarifariaValidacaoCamposException.class)
    public ResponseEntity<Error> faixaTarifariaValidacaoCamposException(FaixaTarifariaValidacaoCamposException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                String.format(ex.getMessage() + "%s.", ex.getNomeCategoria()),
                String.format("As faixas não pode ter intervalos que se cruzem, corrigam o JSON.", ex.getNomeCategoria()),
                FaixaTarifariaValidacaoCamposException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


    @ExceptionHandler(FaixaNaoCobreConsumoException.class)
    public ResponseEntity<Error> faixaNaoCobreConsumoException(FaixaNaoCobreConsumoException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                String.format(ex.getMessage(),ex.getValorConsumo()),
                String.format("O consumo informado não está associada uma faixa de cobrança da categoria %s.",
                        ex.getNomeCategoria()),
                FaixaNaoCobreConsumoException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


    @ExceptionHandler(CalculoRequestException.class)
    public ResponseEntity<Error> calculoRequestException(CalculoRequestException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "Requisição para CalculoRequestDTO, está com alguma campo inválido.",
                CalculoRequestException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }



    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        var problemaPadrao = new Error(
                HttpStatus.BAD_REQUEST.value(),
                "Campo formato inválido.",
                "Algum campo do JSON  está com o formato inválido, revise a documentação.",
                HttpMessageNotReadableException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.badRequest().body(problemaPadrao);
    }


    @ExceptionHandler(TabelaTarifariaNaoLocalizadaException.class)
    public ResponseEntity<Error> tabelaTarifariaNaoLocalizadaException(TabelaTarifariaNaoLocalizadaException ex) {
        var problemaPadrao = new Error(
                HttpStatus.BAD_REQUEST.value(),
                "Tabela tarifaria não localizada",
                ex.getMessage(),
                TabelaTarifariaNaoLocalizadaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.badRequest().body(problemaPadrao);
    }



}
