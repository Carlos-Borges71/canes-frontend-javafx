package com.canes.model;

import java.time.Instant;
import java.util.List;

public class Cliente {

    private Integer id;
    private String nome;
    private Instant instante;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nome, Instant instante, List<Telefone> telefones, List<Endereco> enderecos,
            List<Pedido> pedidos) {

        this.nome = nome;
        this.instante = instante;
        this.telefones = telefones;
        this.enderecos = enderecos;
        this.pedidos = pedidos;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instant getInstante() {
        return instante;
    }

    public void setInstante(Instant instante) {
        this.instante = instante;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

}
