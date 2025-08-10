package com.canes.util;

import javafx.scene.control.TextField;



public class MaskUtil {

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
}
   
