package com.gitgub.diogenesssantos.api.exception;

public class FaixaTarifariaException  extends  RuntimeException{

    private String nomeCategoria;

    public FaixaTarifariaException(String message, String nomeCategoria) {
        super(message);
        this.nomeCategoria = nomeCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }
}
