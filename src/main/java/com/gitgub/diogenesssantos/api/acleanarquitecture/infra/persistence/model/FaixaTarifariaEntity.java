package com.gitgub.diogenesssantos.api.acleanarquitecture.infra.persistence.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "tabela_faixa_tarifaria")
public class FaixaTarifariaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tabela_id")
    private TabelaTarifariaEntity tabela;

    @NotNull(message = "Erro na tabela tarifaria, categoria não pode ser null.")
    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_id")
    private CategoriaEntity categoria;

    private Integer inicio;
    private Integer fim;
    private BigDecimal valorUnitario;
    private Integer ordem;


    protected FaixaTarifariaEntity() {
    }

    public FaixaTarifariaEntity(CategoriaEntity categoria, Integer inicio, Integer fim,
                                BigDecimal valorUnitario, Integer ordem) {
        this.categoria = categoria;
        this.inicio = inicio;
        this.fim = fim;
        this.valorUnitario = valorUnitario;
        this.ordem = ordem;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TabelaTarifariaEntity getTabela() {
        return tabela;
    }

    public void setTabela(TabelaTarifariaEntity tabela) {
        this.tabela = tabela;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
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

    @Override
    public String toString() {
        return "FaixaTarifaria{" +
                "id=" + id +
                ", categoria=" + categoria +
                ", inicio=" + inicio +
                ", fim=" + fim +
                ", valorUnitario=" + valorUnitario +
                ", ordem=" + ordem +
                '}';
    }
}
