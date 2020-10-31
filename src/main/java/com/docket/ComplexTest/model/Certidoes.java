package com.docket.ComplexTest.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Certidoes {

    @Id
    @NotNull
    @GeneratedValue
    private long id;

    @NotNull
    private String nome;

    public Cartorio getCartorio() {
        return cartorio;
    }

    public void setCartorio(Cartorio cartorio) {
        this.cartorio = cartorio;
    }

    @NotNull
    @ManyToOne
    @JoinColumn(name="cartorioId", nullable=false)
    private Cartorio cartorio;

    private long externalCartorioId;

    public long getExternalCartorioId() {
        return externalCartorioId;
    }

    public void setExternalCartorioId(long externalCartorioId) {
        this.externalCartorioId = externalCartorioId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
