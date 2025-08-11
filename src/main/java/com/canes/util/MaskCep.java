package com.canes.util;

import javafx.scene.control.TextField;

public class MaskCep {

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


}

     



        

