package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dto.CustomerDto;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

import java.io.IOException;
import java.util.Optional;

public class MyAccountFormController {
    public Label lblName;
    public TextField txtCity;
    public TextField txtAge;
    public TextField txtEmail;
    public TextField txtUsername;
    public TextField txtCurrentPassword;
    public TextField txtNewPassword;
    public TextField txtConfirmPassword;
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);

    public void initialize(){
        loadCustomer();
    }

    private void loadCustomer(){
        CustomerDto customerDto = customerService.getCustomerUsingUsername(LoginPageController.username);
        lblName.setText(customerDto.getName().getFirstName()+ " "+ customerDto.getName().getMiddleName()+ " "+ customerDto.getName().getLastName());
        txtCity.setText(customerDto.getCity());
        txtAge.setText(String.valueOf(customerDto.getAge()));
        txtEmail.setText(customerDto.getEMail());
        txtUsername.setText(customerDto.getUsername());
    }

    public void changePasswordOnAction(ActionEvent actionEvent) {
        CustomerDto customerDto = customerService.getCustomerUsingUsername(LoginPageController.username);
        if(customerDto.getPassword().equals(txtCurrentPassword.getText())){
            if (!txtNewPassword.equals(null)){
                if (!txtConfirmPassword.equals(null)){
                    if (txtNewPassword.getText().equals(txtConfirmPassword.getText())){
                        customerDto.setPassword(txtConfirmPassword.getText());
                        customerService.update(customerDto);
                        new Alert(Alert.AlertType.CONFIRMATION, "Password Changed!").show();
                    }else {
                        txtConfirmPassword.setStyle("-fx-border-width: 2px; -fx-border-color: red");
                        new Alert(Alert.AlertType.WARNING, "New Password and you confirmed password should be same!").show();
                    }
                }else {
                    new Alert(Alert.AlertType.ERROR, "Please Enter password Again!").show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Please Enter New Password!").show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Entered Password Incorrect!").show();
        }
    }

    public void btnChangeAccountDetailsOnAction(ActionEvent actionEvent) {
        CustomerDto customerDto = customerService.getCustomerUsingUsername(LoginPageController.username);
        customerDto.setCity(txtCity.getText());
        customerDto.setAge(Integer.parseInt(txtAge.getText()));
        customerDto.setEMail(txtEmail.getText());
        customerDto.setUsername(txtUsername.getText());
        if (customerService.update(customerDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "Details Updated!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }

    }

    public void btnDeleteAccountOnAction(ActionEvent actionEvent) throws IOException {
        ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
        ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

        Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

        if (type.orElse(no) == yes) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/goodByeForm.fxml"));
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

            CustomerDto customerDto = customerService.getCustomerUsingUsername(LoginPageController.username);
            //customerService.delete(customerDto);

            System.exit(0);
        }
    }
}
