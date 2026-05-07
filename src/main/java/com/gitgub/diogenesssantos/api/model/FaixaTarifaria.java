package com.gitgub.diogenesssantos.api.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "tabela_faixa_tarifaria")
public class FaixaTarifaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tabela_id")
    private TabelaTarifaria tabela;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_id")
    private Categoria categoria;

    private Integer inicio;
    private Integer fim;
    private BigDecimal valorUnitario;
    private Integer ordem;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TabelaTarifaria getTabela() {
        return tabela;
    }

    public void setTabela(TabelaTarifaria tabela) {
        this.tabela = tabela;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getInicio() {
        return inicio;
    }

    public void setInicio(Integer inicio) {
        this.inicio = inicio;
    }

    public Integer getFim() {
        return fim;
    }

    public void setFim(Integer fim) {
        this.fim = fim;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }
}