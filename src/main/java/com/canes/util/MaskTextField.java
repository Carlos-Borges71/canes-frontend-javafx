package com.canes.util;


import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

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

    public static void limitarCaracteresFixos(TextField textField, int tamanhoFixo) {    
    
        
   
        textField.setTextFormatter(new TextFormatter<>(change -> {
            if (change.getText().isEmpty()) {
                // Permite apagar sempre
                return change;
            }

            // Converte para maiúsculo
            change.setText(change.getText().toUpperCase());

            String newText = change.getControlNewText();

            // limita tamanho
            if (newText.length() > tamanhoFixo) {
                return null;
            }

            // só letras maiúsculas e números
            if (!newText.matches("[A-Z0-9]*")) {
                return null;
            }

            return change;
        }));
    }

    
    

     public static void applyCnpjMask(TextField textField) {
        
    
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == null) return;

            // Remove tudo que não for número
            String digits = newValue.replaceAll("\\D", "");

            // Se apagar tudo → deixa vazio (resolve IllegalArgumentException)
            if (digits.isEmpty()) {
                textField.setText("");
                return;
            }

            // Limita em 14 dígitos
            if (digits.length() > 14) {
                digits = digits.substring(0, 14);
            }

            // Aplica a máscara
            StringBuilder formatted = new StringBuilder();
            int len = digits.length();

            for (int i = 0; i < len; i++) {
                formatted.append(digits.charAt(i));
                if (i == 1) formatted.append(".");
                if (i == 4) formatted.append(".");
                if (i == 7) formatted.append("/");
                if (i == 11) formatted.append("-");
            }

            String finalText = formatted.toString();

            if (!finalText.equals(textField.getText())) {
                textField.setText(finalText);

                // Mantém cursor no final
                Platform.runLater(() -> textField.positionCaret(finalText.length()));
            }
        });
    }

    /**
     * Retorna o CNPJ apenas com números (sem máscara).
     */
    public static String getValueCnpj(TextField field) {
        if (field.getText() == null) return "";
        return field.getText().replaceAll("\\D", "");
    }

        

   
    

    public static void validarNaoVazio(TextField textfield, Button botao) {
        
        botao.disableProperty().bind(Bindings.createBooleanBinding(() ->
        textfield.getText().trim().isEmpty(), textfield.textProperty()
        ));
    }




    
    public static void number(TextField field) {
        field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change; // aceita apenas dígitos
            }
            return null; // bloqueia outros caracteres
        }));
    }

    /**
     * Permite apenas números, limitando a quantidade de dígitos.
     */
    public static void quantNumbery(TextField field, int maxLength) {
        field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d{0," + maxLength + "}")) {
                return change;
            }
            return null;
        }));
    }

    
   
     
    
   
    public static void valor(TextField field) {
        valor(field, new Locale("pt", "BR"), 2);
    }

    

    /**
     * Aplica máscara de moeda configurável (locale e casas decimais).
     */
    public static void valor(TextField field, Locale locale, int fractionDigits) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        nf.setMinimumFractionDigits(fractionDigits);
        nf.setMaximumFractionDigits(fractionDigits);

        AtomicBoolean updating = new AtomicBoolean(false);

        field.textProperty().addListener((obs, oldValue, newValue) -> {
            if (updating.get()) return; // evita recursão
            updating.set(true);
            try {
                // remove tudo que não for dígito
                String digits = newValue == null ? "" : newValue.replaceAll("\\D", "");

                if (digits.isEmpty()) {
                    field.setText(""); // campo vazio quando nada
                    return;
                }

                // converte centavos -> coloca vírgula com fractionDigits
                BigDecimal value = new BigDecimal(digits).movePointLeft(fractionDigits);

                String formatted = nf.format(value);
                field.setText(formatted);

                // posiciona o cursor ao final (em runLater para garantir aplicação)
                Platform.runLater(() -> field.positionCaret(formatted.length()));

            } catch (Exception e) {
                // fallback: restaura valor antigo seguro
                field.setText(oldValue == null ? "" : oldValue);
                Platform.runLater(() -> field.positionCaret(field.getText().length()));
            } finally {
                updating.set(false);
            }
        });
    }

    /**
     * Retorna o valor numérico do campo como BigDecimal (ex: R$ 1.234,56 -> 1234.56).
     * Retorna BigDecimal.ZERO se vazio ou inválido.
     */
    public static BigDecimal getValue(TextField field) {
        String text = field.getText();
        if (text == null || text.isBlank()) return BigDecimal.ZERO;
        String digits = text.replaceAll("\\D", "");
        try {
            return new BigDecimal(digits).movePointLeft(2);
        } catch (NumberFormatException e) {
            return BigDecimal.ZERO;
        }
    }

    
   
}


   



