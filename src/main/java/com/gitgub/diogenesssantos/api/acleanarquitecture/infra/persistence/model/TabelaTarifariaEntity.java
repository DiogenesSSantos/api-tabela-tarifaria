package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tabela_tarifaria")
public class TabelaTarifariaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private LocalDate dataVigencia;
    private boolean ativo = true;
    @OneToMany(mappedBy = "tabela", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<FaixaTarifariaEntity> faixaTarifariaList;


    protected TabelaTarifariaEntity() {
    }

    public TabelaTarifariaEntity(String nome, LocalDate dataVigencia, boolean ativo, List<FaixaTarifariaEntity> faixaTarifariaList) {
        this.nome = nome;
        this.dataVigencia = dataVigencia;
        this.ativo = ativo;
        this.faixaTarifariaList = faixaTarifariaList;
        if (faixaTarifariaList != null) {
            faixaTarifariaList.forEach(faixa -> faixa.setTabela(this));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(LocalDate dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setFaixaTarifariaList(List<FaixaTarifariaEntity> faixaTarifariaList) {
        this.faixaTarifariaList = faixaTarifariaList;
    }

    public List<FaixaTarifariaEntity> getFaixaTarifariaList() {
        return faixaTarifariaList;
    }

    @Override
    public String toString() {
        return "TabelaTarifaria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", dataVigencia=" + dataVigencia +
                ", ativo=" + ativo +
                ", faixaTarifariaList=" + faixaTarifariaList +
                '}';
    }
}