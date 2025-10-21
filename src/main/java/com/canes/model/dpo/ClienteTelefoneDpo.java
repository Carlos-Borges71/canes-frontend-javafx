package com.canes.model.dpo;

import java.time.Instant;

public class ClienteTelefoneDpo {

    private String nome;
    private String telefones;
    private Instant instante;
    private String enderecos;
    private String pedidos;

    public ClienteTelefoneDpo(String nome, Instant instante, String telefones, String enderecos, String pedidos) {
        this.nome = nome;
        this.telefones = telefones;
        this.instante = instante;
        this.enderecos = enderecos;
        this.pedidos = pedidos;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefones() {
        return telefones;
    }

    public Instant getInstante() {
        return instante;
    }

    public String getEnderecos() {
        return enderecos;
    }

    public String getPedidos() {
        return pedidos;
    }

}
