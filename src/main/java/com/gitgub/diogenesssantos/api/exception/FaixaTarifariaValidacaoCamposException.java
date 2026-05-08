package com.gitgub.diogenesssantos.api.exception;

public class FaixaTarifariaValidacaoCamposException extends  RuntimeException{

    private String nomeCategoria;

    public FaixaTarifariaValidacaoCamposException(String message, String nomeCategoria) {
        super(message);
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
