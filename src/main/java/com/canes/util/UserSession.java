package com.canes.util;

public class UserSession {

    private static UserSession instance;
    private Long id;
    private String nomeUsuario;
    private String login;

    private UserSession() {
        // Construtor privado para garantir Singleton
    }

    public static UserSession getInstance() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    public void setUsuario(Long id, String nome, String login) {
        this.id =id;
        this.nomeUsuario = nome;
        this.login = login;
    }
    public Long getId(){
        return id;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getlogin() {
        return login;
    }

    public void limparSessao() {
        instance = null;
    }
}

