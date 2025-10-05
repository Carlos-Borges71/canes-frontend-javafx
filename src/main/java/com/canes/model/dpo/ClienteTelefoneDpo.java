package com.canes.model.dpo;

import java.time.Instant;

public class ClienteTelefoneDpo {

    private String nome;
    private String telefones;
    private Instant instante;
    
    public ClienteTelefoneDpo(String nome, String telefones, Instant instante) {
        this.nome = nome;
        this.telefones = telefones;
        this.instante = instante;
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



        
}
