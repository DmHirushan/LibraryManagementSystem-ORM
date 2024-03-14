package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.util.DateAndTime;

import java.io.IOException;

public class AdminDashboardFormController {
    public Label lblDateAndTime;
    public Button btnCustomerOnAction;
    public AnchorPane contentContext;
    public Button btnHomeOnAction;

    public void initialize() throws IOException {
        DateAndTime.manageDateAndTime(lblDateAndTime);
        setUi("userDashbordContentForm");
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        setUi("customerForm");
    }


    public void btnCustomerClicked(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().add("raised-button");

        button.setStyle("-fx-background-color: #388e3c");
        button.setOnMouseReleased(e -> button.setStyle("-fx-background-color: #393434;"));
    }

    public void btnCustomerReleased(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().add("raised-button");
        button.setStyle("-fx-background-color: #393434");
    }

    private void setUi(String location) throws IOException {
        Parent fxml = FXMLLoader.load(getClass().getResource("/view/"+location+".fxml"));
        contentContext.getChildren().removeAll();
        contentContext.getChildren().setAll(fxml);
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        setUi("userDashbordContentForm");
    }

    public void btnLibraryOnAction(ActionEvent actionEvent) throws IOException {
        setUi("libraryForm");
    }

    public void btnBranchesOnAction(ActionEvent actionEvent) throws IOException {
        setUi("branchForm");
    }

    public void btnNotReturned(ActionEvent actionEvent) throws IOException {
        setUi("notReturnedForm");
    }
}
