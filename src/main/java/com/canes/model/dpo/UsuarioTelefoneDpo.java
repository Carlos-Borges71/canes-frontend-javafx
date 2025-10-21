package com.canes.model.dpo;

import java.time.Instant;

public class UsuarioTelefoneDpo {

    
    private String nome;    
    private String login;
    private String setor;   
    private Instant instante;
    
    private String telefones;
    private String enderecos;

   

    public UsuarioTelefoneDpo(){        
    }

    public UsuarioTelefoneDpo( String nome, String setor,  String login, Instant instante, String telefones, String enderecos) {
        
        this.setor = setor;
        this.nome = nome;
        this.login = login;
        this.instante = instante;    
        this.telefones = telefones;
        this.enderecos = enderecos;
        
    }
  

    

    
    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Instant getInstante() {
        return instante;
    }

    public void setInstante(Instant instante) {
        this.instante = instante;
    }

    
    
    public String getTelefone() {
        return telefones;
    }

    public void setTelefone(String telefones) {
        this.telefones = telefones;
    }

    public String getEnderecos() {
        return enderecos;
    }



}
