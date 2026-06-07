package com.gitgub.diogenesssantos.api.exceptionhandler;


import com.gitgub.diogenesssantos.api.exception.*;
import com.gitgub.diogenesssantos.api.model.Categoria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.exc.InvalidFormatException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> handleConstraintViolation(
            MethodArgumentNotValidException ex) {

        List<Error.Campos> erros = ex.getFieldErrors().stream()
                .map(fieldError -> new Error.Campos(fieldError.getField().replaceAll(".*\\.", "")
                        , fieldError.getDefaultMessage()))
                .toList();

        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                String.format("Erro JSON campos inválidos."),
                String.format("O corpo da requisição incorreto, observe os campos abaixo inválidos e corrigia seguindo a instrução."),
                MethodArgumentNotValidException.class.getSimpleName(),
                LocalDateTime.now(),
                erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);
    }


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


    @ExceptionHandler(TabelaTarifariaNullException.class)
    public ResponseEntity<Error> tabelaTarifariaNullException(TabelaTarifariaNullException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "A tabela tarifaria não pode ser null",
                TabelaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


    @ExceptionHandler(TabelaTarifariaNaoAtivaException.class)
    public ResponseEntity<Error> tabelaTarifariaNaoAtivaException(TabelaTarifariaNaoAtivaException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "Não existe nenhuma tabela tarifaria ativo no banco de dados, crie um tabela tarifaria.",
                TabelaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }


    @ExceptionHandler(TabelaTarifariaNomeException.class)
    public ResponseEntity<Error> TabelaTarifariaNomeException(TabelaTarifariaNomeException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "O nome não pode ser null ou vázio.",
                TabelaTarifariaException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problema);

    }

    @ExceptionHandler(TabelaTarifariaDataVigenciaException.class)
    public ResponseEntity<Error> tabelaTarifariaDataVigenciaException(TabelaTarifariaDataVigenciaException ex) {
        var problema = new Error(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "A data vigência não pode ser nula, passe uma data valida.",
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
                String.format(ex.getMessage(), ex.getValorConsumo()),
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


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        String mensagemUsuario = "O JSON enviado está malformado. Verifique se todos os campos possuem valores válidos.";

        Throwable causa = ex.getCause();

        if (causa instanceof InvalidFormatException invalidFormat
                && invalidFormat.getTargetType() != null
                && invalidFormat.getTargetType().isEnum()) {

            String valorInformado = invalidFormat.getValue().toString();
            String valoresValidos = Arrays.stream(invalidFormat.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .collect(Collectors.joining(", "));

            mensagemUsuario = "O valor '%s' é inválido para o campo '%s'. Valores aceitos: [%s]"
                    .formatted(
                            valorInformado,
                            invalidFormat.getPath().getLast().getPropertyName(),
                            valoresValidos
                    );

        } else if (ex.getMessage() != null && ex.getMessage().contains("Cannot coerce empty String")) {
            String valoresValidos = Arrays.stream(Categoria.values())
                    .map(Enum::name)
                    .collect(Collectors.joining(", "));
            mensagemUsuario = "O campo 'categoria' não pode ser vazio. Valores aceitos: [%s]"
                    .formatted(valoresValidos);

        }


        var problemaPadrao = new Error(
                HttpStatus.BAD_REQUEST.value(),
                "Campo formato inválido.",
                mensagemUsuario,
                HttpMessageNotReadableException.class.getSimpleName(),
                LocalDateTime.now(),
                null);

        return ResponseEntity.badRequest().body(problemaPadrao);
    }

}
