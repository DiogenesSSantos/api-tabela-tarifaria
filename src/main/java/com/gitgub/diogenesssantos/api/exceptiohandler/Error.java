package com.gitgub.diogenesssantos.api.exceptiohandler;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record Error(
        int statusCode,
        String mensagem,
        String mensagemUsuario,
        String classException,
        LocalDateTime timeStamp,

        List<Campos> errorsCampos
) {

    record Campos(String campo, String mensagem ) {}


}