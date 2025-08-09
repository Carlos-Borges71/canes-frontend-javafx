package com.canes.util;

import javafx.scene.control.TextField;

public class MaskUtil {

    public void applyPhoneMask(TextField textField){
        final boolean[] isUpdating = {false};
 
        textField.textProperty().addListener((obs, oldValue, newValue) -> {

            if(isUpdating[0]) return;

            String digits = newValue.replaceAll("[^\\d]", "");

            if (digits.length() > 11 ){
                digits = digits.substring(0,11);
            }

            StringBuilder formatted = new StringBuilder();

            int len = digits.length();
            if(len > 0) { 
                formatted.append("(");

                //if (len >=1) formatted.append(digits.substring(0,Math.min(2, len)));

            if (len >= 2) {
                formatted.append(digits.substring(0,2)).append(")");

            }else {
                formatted.append(digits);
            }
            
            


            if (len > 2 && len <= 6) {
                formatted.append(digits.substring(2));

            }else if (len > 6) {
            formatted.append(digits.substring(2,7)).append("-");
            
            formatted.append(digits.substring(7));
                
            }
            
            }

           String masked = formatted.toString();

          isUpdating[0] = true;
          textField.setText(masked);

          textField.positionCaret(masked.length());
          
          isUpdating[0] = false;

    

        });


    }

}    
