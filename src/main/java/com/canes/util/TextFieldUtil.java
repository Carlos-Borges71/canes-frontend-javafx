package com.canes.util;
import javafx.scene.control.TextField;

public class TextFieldUtil {

    /**
     * Aplica formatação para deixar a primeira letra de cada palavra em maiúsculo.
     * @param textField Campo de texto que receberá a formatação
     */
    public static void aplicarCapitalizacao(TextField textField) {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                String formatado = capitalizarCadaPalavra(newValue);
                if (!formatado.equals(newValue)) {
                    textField.setText(formatado);
                }
            }
        });
    }

    /**
     * Capitaliza a primeira letra de cada palavra, deixando o restante minúsculo.
     */
    private static String capitalizarCadaPalavra(String texto) {
        String[] palavras = texto.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String palavra : palavras) {
            if (!palavra.isEmpty()) {
                sb.append(palavra.substring(0, 1).toUpperCase());
                if (palavra.length() > 1) {
                    sb.append(palavra.substring(1).toLowerCase());
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
}