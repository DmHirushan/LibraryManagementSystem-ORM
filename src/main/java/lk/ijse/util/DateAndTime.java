package lk.ijse.util;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateAndTime {
    public static void manageDateAndTime(Label label){
        Timeline timeAndDate = new Timeline(new KeyFrame(Duration.ZERO, e->label.setText(LocalDateTime
                .now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd     HH:mm:ss")))),
                new KeyFrame(Duration.seconds(1)));
        timeAndDate.setCycleCount(Animation.INDEFINITE);
        timeAndDate.play();
    }
}
