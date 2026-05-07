package com.gitgub.diogenesssantos.api.exception;

public class CategoriaNomeInvalidoException extends RuntimeException{

    private String nomeCategoria;


    public CategoriaNomeInvalidoException(String message, String categoriaNome) {
        super(message);
        this.nomeCategoria = categoriaNome;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
