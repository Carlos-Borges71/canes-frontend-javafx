package com.canes.model;

import java.util.List;

public class Cliente {

    private Long id;
    private String nome;
    private String instante;
    private List<Telefone> telefones;
    private Endereco endereco;
    private List<Pedido> pedidos;
    private List<Usuario>usuarios;

    public Cliente() {
    }

    public Cliente(String nome, String instante) {

        this.nome = nome;
        this.instante = instante;

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

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    

}
