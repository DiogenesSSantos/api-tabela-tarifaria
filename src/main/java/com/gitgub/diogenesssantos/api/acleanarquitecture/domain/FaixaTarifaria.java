package com.gitgub.diogenesssantos.api.acleanarquitecture.domain;

import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaValidacaoCamposException;

import java.math.BigDecimal;
import java.util.Objects;


public class FaixaTarifaria {

    private final Categoria categoria;
    private final Integer inicio;
    private final Integer fim;
    private final BigDecimal valorUnitario;
    private final Integer ordem;


    public FaixaTarifaria(Categoria categoria, Integer inicio, Integer fim, BigDecimal valorUnitario, Integer ordem) {
        this.categoria = Objects.requireNonNull(categoria, "Erro: categoria não pode ser null.");
        this.inicio = Objects.requireNonNull(inicio, "Erro: inicio não pode ser null.");
        this.fim = Objects.requireNonNull(fim, "Erro: fim não pode ser null.");
        this.valorUnitario = Objects.requireNonNull(valorUnitario, "Erro: valor unitário não pode ser null.");
        this.ordem = Objects.requireNonNull(ordem, "Erro: ordem não pode ser null.");

        validaFaixaTarifaria();
    }


    private void validaFaixaTarifaria() {
        if (fim <= inicio)
            throw new FaixaTarifariaValidacaoCamposException(
                    "Erro: fim deve ser maior que inicio na categoria ", categoria.name());

        if (valorUnitario.signum() == -1) {
            throw new FaixaTarifariaValidacaoCamposException(
                    "Erro: valor unitário deve ser 0 ou maior ", categoria.name());
        }
    }


    public Categoria getCategoria() {
        return categoria;
    }

    public Integer getInicio() {
        return inicio;
    }

    public Integer getFim() {
        return fim;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public Integer getOrdem() {
        return ordem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaixaTarifaria that = (FaixaTarifaria) o;
        return categoria == that.categoria
                && Objects.equals(inicio, that.inicio)
                && Objects.equals(fim, that.fim)
                && Objects.equals(valorUnitario, that.valorUnitario)
                && Objects.equals(ordem, that.ordem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoria, inicio, fim, valorUnitario, ordem);
    }

    @Override
    public String toString() {
        return "FaixaTarifaria{" +
                "categoria=" + categoria +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", valorUnitario=" + valorUnitario +
                ", ordem=" + ordem +
                '}';
    }
}
