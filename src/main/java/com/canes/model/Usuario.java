package com.canes.model;

import java.time.Instant;

public class Usuario {

    
    private Integer id;
    private String nome;    
    private String login;
    private String setor;   
    private Instant instante;
    
    private String telefone;

   

    public Usuario(){        
    }

    public Usuario( Integer id,String nome, String setor,  String login, Instant instante,  String telefone) {
        this.id = id;
        this.setor = setor;
        this.nome = nome;
        this.login = login;
        this.instante = instante;    
        this.telefone = telefone;
        
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
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
     
    

 
    
}
