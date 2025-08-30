package com.canes.util;


import java.io.IOException;
import java.util.function.Consumer;

import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScreenUtils {

    /**
     * Troca a tela atual para a informada.
     *
     * @param event      O evento do botão ou outro elemento.
     * @param fxmlFile   Caminho do arquivo FXML.
     * @param title      Título da janela.
     * @param controllerConsumer Código para enviar dados para o novo controller (pode ser null).
     */
    public static void changeScreen(javafx.event.ActionEvent event, String fxmlFile, String title, Consumer<Object> controllerConsumer) throws IOException {

        // FXMLLoader loader = new FXMLLoader(ScreenUtils.class.getResource(fxmlFile));
        // Parent root = loader.load();

        // Se quiser passar dados para o próximo controller
        // if (controllerConsumer != null) {
        //     Object controller = loader.getController();
        //     controllerConsumer.accept(controller);
        // }

        // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // Scene scene = new Scene(root);
        // stage.setTitle(title);
        // stage.setScene(scene);        
    

        // stage.show();

         Parent root = FXMLLoader.load(ScreenUtils.class.getResource(fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));

            stage.setMaximized(true);

            stage.show();

            
        

        
        // Animação Fade-in
        
        FadeTransition ft = new FadeTransition(Duration.millis(1500), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();

        // TranslateTransition slide = new TranslateTransition(Duration.millis(1600), root);
        // slide.setFromX(-300);
        // slide.setToX(0);
        // slide.play();

        // ScaleTransition scale = new ScaleTransition(Duration.millis(1600), root);
        // scale.setFromX(0.5);
        // scale.setFromY(0.5);
        // scale.setToX(1);
        // scale.setToY(1);
        // scale.play();

        // RotateTransition rotate = new RotateTransition(Duration.millis(800), root);
        // rotate.setByAngle(360);
        // rotate.play();

        // Path path = new Path();
        // path.getElements().add(new MoveTo(0, 0));
        // path.getElements().add(new LineTo(200, 200));

        // PathTransition pathTransition = new PathTransition(Duration.seconds(2), path, root);
        // pathTransition.play();

        Stage stageFc = (Stage) ((Node)event.getSource()).getScene().getWindow();
            stageFc.close();
    }

    /**
     * Abre uma nova janela separada.
     */
    public static void openNewWindow(String fxmlFile, String title,
                                     Consumer<Object> controllerConsumer) throws IOException {

        Parent root = FXMLLoader.load(ScreenUtils.class.getResource(fxmlFile));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));    

            stage.show();


        FadeTransition ft = new FadeTransition(Duration.millis(300), root);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}
