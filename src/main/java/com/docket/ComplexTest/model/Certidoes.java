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
