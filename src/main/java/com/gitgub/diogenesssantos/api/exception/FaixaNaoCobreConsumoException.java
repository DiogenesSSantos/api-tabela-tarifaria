package com.gitgub.diogenesssantos.api.exception;

public class FaixaNaoCobreConsumoException extends  RuntimeException{

    private String nomeCategoria;
    private Integer valorConsumo;

    public FaixaNaoCobreConsumoException(String message, String nomeCategoria, Integer valorConsumo) {
        super(message);
        this.nomeCategoria = nomeCategoria;
        this.valorConsumo = valorConsumo;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public Integer getValorConsumo() {
        return valorConsumo;
    }
}