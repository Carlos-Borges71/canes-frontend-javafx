package com.canes.model;

public class Usuario {

    
    private Long id;
    private String nome;    
    private String login;
    private String setor;   
    private String instante;
    private String senha;
    
    // private List<Telefone> telefones;
    private Endereco endereco;
    // private List<Cliente> clientes;
   

    public Usuario(){        
    }

    public Usuario( String nome, String setor,  String login, String instante, String senha) {
        
        this.setor = setor;
        this.nome = nome;
        this.login = login;
        this.instante = instante;    
        //this.telefones = telefones;
        //this.endereco = endereco;
        //this.clientes = clientes;
        this.senha = senha;
        
    }
  
    public Long getId() {
        return id;
    }
    public void setSetId(Long id) {
        this.id = id;
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setInstante(String instante) {
        this.instante = instante;
    }

    
    
    // public List<Telefone> getTelefone() {
    //     return telefones;
    // }

    // public void setTelefone(List<Telefone> telefones) {
    //     this.telefones = telefones;
    // }

    public Endereco getEndereco() {
        return endereco;
    }
     
    // public List<Cliente> getClientes(){
    //     return clientes;
    // }
    
 
    
}
