package com.gitgub.diogenesssantos.api.exception;

public class TabelaTarifariaException  extends RuntimeException{

    private String nomeTabela;


    public TabelaTarifariaException(String message, String nomeTabela) {
        super(message);
        this.nomeTabela = nomeTabela;
    }

    public String getNomeTabela() {
        return nomeTabela;
    }
}
