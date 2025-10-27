package com.canes.model.dpo;

import java.time.Instant;

import com.canes.model.Endereco;

public class UsuarioTelefoneDpo {

    private int id;
    private String nome;    
    private String login;
    private String setor;   
    private String instante;
    private String senha;
    
    //private String telefones;
    private Endereco endereco;
   //private String clientes;
   

    public UsuarioTelefoneDpo(){        
    }

    public UsuarioTelefoneDpo( String nome, String setor,  String login, String instante, String senha, Endereco endereco) {
        
        this.setor = setor;
        this.nome = nome;
        this.login = login;
        this.instante = instante;    
        //this.telefones = telefones;
        this.endereco = endereco;
        //this.clientes = clientes;
        this.senha = senha;
        
    }
  
    public int getId() {
        return id;
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

    public String getInstante() {
        return instante;
    }

    public void setInstante(String instante) {
        this.instante = instante;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    
    
    // public String getTelefone() {
    //     return telefones;
    // }

    // public void setTelefone(String telefones) {
    //     this.telefones = telefones;
    // }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    //  public String getClientes(){
    //     return clientes;
    // }

}
