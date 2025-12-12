package com.canes.util;

import java.net.HttpURLConnection;
import java.net.URL;

public class ConexaoUtil {

    // Testa conexão tentando acessar o Google (rápido e confiável)
    public static boolean isConectado() {
        try {
            URL url = new URL("https://www.google.com");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(2000); // timeout 2s
            conn.connect();
            return (conn.getResponseCode() == 200);
        } catch (Exception e) {
            return false; // sem internet
        }
    }

    public static boolean isConectadoAPI() {
        try {
            URL url = new URL("http://localhost:8080/usuarios");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(2000); // timeout 2s
            conn.connect();
            return (conn.getResponseCode() == 200);
        } catch (Exception e) {
            return false; // sem internet
        }
    }
}

