package com.canes.util;

import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MaskTextField {

    public static void applyCepMask(TextField textField) {

        final boolean[] isUpdating = {false};        

        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (isUpdating[0]) return;

            // Remove qualquer caractere que não seja número
            String digits = newValue.replaceAll("[^\\d]", "");

            // Limita a 8 dígitos
            if (digits.length() > 8)
                digits = digits.substring(0, 8);

            StringBuilder formatted = new StringBuilder();
            int len = digits.length();

            if (len > 5) {
                formatted.append(digits.substring(0, 5))
                         .append("-")
                         .append(digits.substring(5));
            } else {
                formatted.append(digits);
            }

            String masked = formatted.toString();

            isUpdating[0] = true;
            textField.setText(masked);
            textField.positionCaret(masked.length()); // mantém o cursor no fim
            isUpdating[0] = false;
   });
    }

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

    public static void applyPhoneMask(TextField textField) {
        final boolean[] isUpdating = {false};

        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (isUpdating[0]) return;

            // Remove tudo que não for número
            String digits = newValue.replaceAll("[^\\d]", "");

            // Limita a 11 dígitos
            if (digits.length() > 11)
                digits = digits.substring(0, 11);

            StringBuilder formatted = new StringBuilder();
            int len = digits.length();

            if (len >= 1) formatted.append("(");

            if (len >= 2) {
                formatted.append(digits.substring(0, 2)).append(") ");
            } else if (len == 1) {
                formatted.append(digits.charAt(0));
            }

            if (len > 2 && len <= 6) {
                formatted.append(digits.substring(2));
            } else if (len > 6) {
                formatted.append(digits.substring(2, 7)).append("-");
                formatted.append(digits.substring(7));
            }

            String masked = formatted.toString();

            // Evita loop
            isUpdating[0] = true;
            textField.setText(masked);
            textField.positionCaret(masked.length()); // cursor no final
            isUpdating[0] = false;
        });
    }

    public static void limitarCaracteresFixos(TextField textField, int tamanhoFixo, Button botao) {

        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if(newValue != null){
                if(newValue.length() > tamanhoFixo) {
                    textField.setText(newValue.substring(0, tamanhoFixo));
                }

                //botao.setDisable(newValue.length() < tamanhoFixo);
            }
        });
    }

    public static void validarNaoVazio(TextField textfield, Button botao) {
        
        botao.disableProperty().bind(Bindings.createBooleanBinding(() ->
        textfield.getText().trim().isEmpty(), textfield.textProperty()
        ));
    }

}
