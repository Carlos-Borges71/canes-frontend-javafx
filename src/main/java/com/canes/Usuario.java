package com.canes;

public class Usuario {

    private String nome;
    private String setor;
    private String login;
    private String telefone;


    public Usuario(String nome, String setor, String login, String telefone) {
        this.nome = nome;
        this.setor = setor;
        this.login = login;
        this.telefone = telefone;
    }

    
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    

}
