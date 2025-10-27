package com.canes.model;

public class Telefone {

    private Long id;
    private String numero;
    private Usuario operador;

    public Telefone() {
    }

    public Telefone(String numero) {

        this.numero = numero;

    }

    public Long getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    

}
