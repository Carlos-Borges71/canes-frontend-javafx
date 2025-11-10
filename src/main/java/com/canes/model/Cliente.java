package com.canes.model;

import java.util.List;


public class Cliente {

    private Long id;
    private String nome;
    private String instante;
    private String telefones;
    private Endereco endereco;
    private List<Pedido> pedidos;
    private List<Usuario> usuarios;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String instante, Endereco endereco, String telefones) {

        this.id = id;
        this.nome = nome;
        this.instante = instante;
        //this.operador = operador;
        this.endereco = endereco;
        this.telefones =telefones;

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

    public Endereco getEndereco() {
        return endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setTelefones(String telefones) {
        this.telefones = telefones;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    

}
