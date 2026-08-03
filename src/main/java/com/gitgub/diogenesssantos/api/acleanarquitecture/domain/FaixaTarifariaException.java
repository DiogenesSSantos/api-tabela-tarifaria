package com.gitgub.diogenesssantos.api.acleanarquitecture.domain;

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
