package com.canes.util;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class RelogioUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public static void iniciarRelogio(Label label){
        Timeline timeLine = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                label. setText(LocalTime.now().format(FORMATTER));
            })
        );
        timeLine.setCycleCount(Timeline.INDEFINITE);
        timeLine.play();
    }
}
