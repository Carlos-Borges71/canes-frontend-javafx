package com.canes.util;

public class UserSession {

    private static UserSession instance;
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

    public void setUsuario(String nome, String login) {
        this.nomeUsuario = nome;
        this.login = login;
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
