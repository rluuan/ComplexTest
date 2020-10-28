package com.docket.ComplexTest.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Cartorio {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    private String nome;

    @NotNull
    private String rua;

    @NotNull
    private String bairro;

    @NotNull
    private String numero;

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

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }



}
