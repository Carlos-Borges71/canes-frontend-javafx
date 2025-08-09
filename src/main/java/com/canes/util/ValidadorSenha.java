package com.canes.util;

public class ValidadorSenha {

    public static boolean  isSenhaValida(String senha){

        return senha !=null && senha.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }
}
