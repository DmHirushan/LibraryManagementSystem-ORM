package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SuccessfulAlertFormController {
    public Label label;
    public void initialize(){
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            // Close the window after 3 seconds
            label.getScene().getWindow().hide();
        }));
        timeline.play();
    }
}
