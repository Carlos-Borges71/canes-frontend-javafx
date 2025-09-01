package com.canes.util;


import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;

public class AlertUtil {

    public static void mostrarErro(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensagem, ButtonType.OK);
        
        alert.setHeaderText(null);

        // Remove barra do sistema e deixa a janela transparente
        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);


        // Estilização moderna do container
        alert.getDialogPane().setStyle(
            "-fx-background-color: #919090ff;" +         // fundo escuro
            "-fx-background-radius: 25;" +             // cantos arredondados
            "-fx-border-radius: 25;" +
            "-fx-padding: 20;" + 
            "-fx-text-fill: white;" +                      // espaço interno
            "-fx-font-size: 18px;" +
            "-fx-font-weight: bold;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 5);" // sombra suave
        );

        // Estilo do botão OK
        alert.getDialogPane().lookupButton(ButtonType.OK).setStyle(
            "-fx-background-color: #FF5555;" +         // cor principal (vermelho erro)
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 25;" +
            "-fx-cursor: hand;" +
            
            "-fx-border-radius: 25;"
        );

        alert.showAndWait();
    }

    public static void mostrarSucesso(String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, mensagem, ButtonType.OK);
    
        alert.setHeaderText(null);

        alert.initStyle(StageStyle.TRANSPARENT);
        alert.getDialogPane().getScene().setFill(Color.TRANSPARENT);

        alert.getDialogPane().setStyle(
            "-fx-background-color: #919090ff;" +
            "-fx-background-radius: 15;" +
            "-fx-border-radius: 15;" +
            "-fx-padding: 20;" +
            "-fx-font-size: 14px;" +
            "-fx-text-fill: white;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 15, 0, 0, 5);"
        );

        alert.getDialogPane().lookupButton(ButtonType.OK).setStyle(
            "-fx-background-color: #4CAF50;" +         // verde sucesso
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 10;" +
            "-fx-border-radius: 10;" +
            "-fx-cursor: hand;" 
        );

        alert.showAndWait();
    }
}