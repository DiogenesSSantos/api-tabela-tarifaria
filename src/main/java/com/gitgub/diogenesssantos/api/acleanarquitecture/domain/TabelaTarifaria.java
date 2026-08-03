package com.gitgub.diogenesssantos.api.acleanarquitecture.domain;


import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaException;
import com.gitgub.diogenesssantos.api.exception.FaixaTarifariaValidacaoCamposException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class TabelaTarifaria {

    private String nome;
    private LocalDate dataVigencia;
    private boolean ativo;
    private List<FaixaTarifaria> faixaTarifariaList;


    public TabelaTarifaria(String nome, LocalDate dataVigencia, boolean ativo, List<FaixaTarifaria> faixaTarifariaList) {
        this.nome = nome;
        this.dataVigencia = dataVigencia;
        this.ativo = ativo;
        this.faixaTarifariaList = Objects.requireNonNull(faixaTarifariaList,
                "Erro: a lista de faixas tarifárias não pode ser null.");

        validaFaixasTabelaTarifaria();
    }

    private void validaFaixasTabelaTarifaria() {

        if (faixaTarifariaList.isEmpty())
            throw new FaixaTarifariaException(
                    "Erro na criação da faixa tarifaria da tabela " + nome, "Não possuo categoria nenhuma");

        Integer fimAnterior = null;
        Integer ordemAnterior = null;

        for (var faixa : faixaTarifariaList) {
            int inicio = faixa.getInicio();
            int fim = faixa.getFim();
            int ordem = faixa.getOrdem();

            if (fim <= inicio)
                throw new FaixaTarifariaValidacaoCamposException(
                        "Erro: fim deve ser maior que inicio na categoria ", faixa.getCategoria().name());

            if (ordemAnterior != null && ordem <= ordemAnterior)
                throw new FaixaTarifariaValidacaoCamposException(
                        "Erro no campo ordem da categoria ", faixa.getCategoria().name());

            if (fimAnterior == null) {
                if (inicio != 0)
                    throw new FaixaTarifariaValidacaoCamposException(
                            "Erro: a primeira faixa deve começar com inicio = 0 na categoria ", faixa.getCategoria().name());
            } else {
                if (inicio != fimAnterior + 1)
                    throw new FaixaTarifariaValidacaoCamposException(
                            "Erro no campo inicio : o inicio da faixa atual deve ser  " +
                                    "> que fim da faixa anterior pelo menos +1 na categoria ", faixa.getCategoria().name());
            }

            fimAnterior = fim;
            ordemAnterior = ordem;
        }
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataVigencia() {
        return dataVigencia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public List<FaixaTarifaria> getFaixaTarifariaList() {
        return faixaTarifariaList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TabelaTarifaria that = (TabelaTarifaria) o;
        return ativo == that.ativo
                && Objects.equals(nome, that.nome)
                && Objects.equals(dataVigencia, that.dataVigencia)
                && Objects.equals(faixaTarifariaList, that.faixaTarifariaList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, dataVigencia, ativo, faixaTarifariaList);
    }

    @Override
    public String toString() {
        return "TabelaTarifaria{" +
                "nome='" + nome + '\'' +
                ", dataVigencia=" + dataVigencia +
                ", ativo=" + ativo +
                ", faixaTarifariaList=" + faixaTarifariaList +
                '}';
    }
}
