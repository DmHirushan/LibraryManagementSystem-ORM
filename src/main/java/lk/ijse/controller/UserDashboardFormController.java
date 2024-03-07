package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.DateAndTime;

import java.io.IOException;

public class UserDashboardFormController {
    public Label lblDateAndTime;
    public AnchorPane contentContext;

    public void initialize(){
        DateAndTime.manageDateAndTime(lblDateAndTime);

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
}
