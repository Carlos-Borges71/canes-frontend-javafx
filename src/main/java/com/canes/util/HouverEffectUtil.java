package com.canes.util;

import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public class HouverEffectUtil {

    public static void apllyHouverSobre(Button button) { 

        DropShadow sombra = new DropShadow();


        sombra.setRadius(10);
        sombra.setOffsetX(0);
        sombra.setOffsetY(0);

        sombra.setColor(Color.BLUE);

        ScaleTransition aumentar = new ScaleTransition(Duration.millis(200), button);

        aumentar.setToX(1.2);
        aumentar.setToY(1.2);

       


        button.setEffect(sombra);
        aumentar.playFromStart();
        
    }

    public static void apllyHouverSair(Button button) { 

         ScaleTransition reduzir = new ScaleTransition(Duration.millis(200), button);

        reduzir.setToX(1);
        reduzir.setToY(1);

        button.setEffect(null);
        reduzir.playFromStart();
        
    }

}
