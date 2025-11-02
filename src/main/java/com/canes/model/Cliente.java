package com.canes.model;

public class Cliente {

    private Long id;
    private String nome;
    private String instante;
    private String telefones;
    private String endereco;
    private String pedidos;
    private String operador;

    public Cliente() {
    }

    public Cliente(String nome, String instante, String operador) {

        this.nome = nome;
        this.instante = instante;
        this.operador = operador;

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

    public String getInstante() {
        return instante;
    }

    public void setInstante(String instante) {
        this.instante = instante;
    }

    public String getTelefones() {
        return telefones;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getPedidos() {
        return pedidos;
    }

    public String getOperador() {
        return operador;
    }
    

}
