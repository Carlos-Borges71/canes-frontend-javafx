package com.canes.util;
import javafx.scene.control.TextField;

public class TextFieldUtil {

    private static boolean updating = false;

    public static void aplicarCapitalizacao(TextField textField) {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (updating) return;

            if (newValue != null && !newValue.isEmpty()) {
                String formatado = capitalizarCadaPalavra(newValue);

                if (!formatado.equals(newValue)) {
                    updating = true;

                    int posCursor = textField.getCaretPosition();

                    // Calcula quantos espaços tem antes do cursor na string antiga e na nova
                    int numEspacosAntesCursorNovo = contarEspacos(formatado.substring(0, Math.min(posCursor, formatado.length())));
                    int numEspacosAntesCursorVelho = contarEspacos(newValue.substring(0, Math.min(posCursor, newValue.length())));

                    textField.setText(formatado);

                    // Ajusta a posição do cursor considerando se o número de espaços mudou
                    int ajusteCursor = posCursor + (numEspacosAntesCursorNovo - numEspacosAntesCursorVelho);
                    if (ajusteCursor > formatado.length()) {
                        ajusteCursor = formatado.length();
                    }
                    if (ajusteCursor < 0) {
                        ajusteCursor = 0;
                    }
                    textField.positionCaret(ajusteCursor);

                    updating = false;
                }
            }
        });
    }

    private static int contarEspacos(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == ' ') count++;
        }
        return count;
    }

    private static String capitalizarCadaPalavra(String texto) {
        String[] palavras = texto.split(" ", -1); // Mantém espaços finais e consecutivos
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            if (!palavra.isEmpty()) {
                sb.append(palavra.substring(0, 1).toUpperCase());
                if (palavra.length() > 1) {
                    sb.append(palavra.substring(1).toLowerCase());
                }
            }
            if (i < palavras.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}