package lk.ijse.controller;

import javafx.animation.RotateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GoodByeFormController {
    public ImageView imgHand;

    public void initialize(){
        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(1), imgHand);
        rotateTransition.setByAngle(90); // Rotate 360 degrees
        rotateTransition.setCycleCount(1); // Rotate once
        rotateTransition.play(); // Start the rotation animation
    }
}
