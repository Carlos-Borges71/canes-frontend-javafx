package com.canes.util;

import javafx.scene.control.TextField;

public class MaskEstado {


    public static void applyStateMask(TextField textField) {
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue != null) {
                // Remove qualquer caractere que não seja letra
                String lettersOnly = newValue.replaceAll("[^a-zA-Z]", "");
                // Limita para no máximo 2 caracteres
                if (lettersOnly.length() > 2) {
                    lettersOnly = lettersOnly.substring(0, 2);
                }
                // Converte para maiúsculas
                String upperCase = lettersOnly.toUpperCase();
                // Atualiza o campo apenas se mudou
                if (!upperCase.equals(newValue)) {
                    textField.setText(upperCase);
                }
            }
        });
    }

}
