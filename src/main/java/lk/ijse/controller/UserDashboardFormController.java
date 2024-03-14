package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.util.DateAndTime;

import java.io.IOException;

public class UserDashboardFormController {
    public Label lblDateAndTime;
    public AnchorPane contentContext;
    public AnchorPane userDashboardContext;

    public void initialize() throws IOException {
        DateAndTime.manageDateAndTime(lblDateAndTime);
        setUi("userDashbordContentForm");
    }

    public void btnLibraryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("userLibraryForm");
    }

    private void setUi(String location) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/"+location+".fxml"));
        contentContext.getChildren().removeAll();
        contentContext.getChildren().setAll(fxml);
    }

    public void btnImOnAction(ActionEvent actionEvent) throws IOException {
        setUi("userDetailForm");
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("userDashbordContentForm");
    }

    public void btnlogOutOnAction(ActionEvent actionEvent) throws IOException {
        logingOut();
        //setUi("loginForm");
    }

    private void logingOut() throws IOException {
        LoginPageController.closeStage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loggedOutForm.fxml"));
        Parent root = loader.load();

        // Set the scene
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();

        // Close the stage after one second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.8), event -> stage.close()));
        timeline.play();


        Stage stage1 = new Stage();

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene1 = new Scene(rootNode);
        stage1.centerOnScreen();
        stage1.setScene(scene1);
        stage1.show();

    }


    public void btnMyAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("myAccountForm");
    }
}
