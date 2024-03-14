package lk.ijse.controller;

import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class LoggedOutFormController {
    public ImageView imgLoading;

    public void initialize(){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), imgLoading);
        rotateTransition.setByAngle(-360); // Rotate 360 degrees
        rotateTransition.setCycleCount(1); // Rotate once
        rotateTransition.play(); // Start the rotation animation
    }
}
