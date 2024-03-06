package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginDetailForm1Controller {
    public TextField txtUsername;
    public TextField txtPassword;

    public void btnloginonAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/adminDashboardForm.fxml"));
        Parent root = loader.load();
        Stage primaryStage = new Stage();
        primaryStage.show();
    }
}
