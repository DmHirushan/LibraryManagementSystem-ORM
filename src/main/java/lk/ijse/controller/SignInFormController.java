package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import lk.ijse.dto.CustomerDto;
import lk.ijse.embeded.NameIdentifier;
import lk.ijse.entity.Customer;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SignInFormController {
    public TextField txtFname;
    public TextField txtMname;
    public TextField txtLname;
    public TextField txtCity;
    public TextField txtAge;
    public TextField txtEmail;
    public TextField txtUsername;
    public TextField txtPassword;
    public TextField txtConfirmPassword;
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    boolean validate = false;

    public void initialize(){
        validateCustomer();
    }
    public void txtSignOnAction(ActionEvent actionEvent) {

        Long id = Long.valueOf(0);
        if (validate) {
            if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
                NameIdentifier nameIdentifier = new NameIdentifier(
                        txtFname.getText(),
                        txtMname.getText(),
                        txtLname.getText()
                );
                id = customerService.save(new CustomerDto(
                        nameIdentifier,
                        Integer.parseInt(txtAge.getText()),
                        txtCity.getText(),
                        txtEmail.getText(),
                        txtUsername.getText(),
                        txtPassword.getText()
                ));
            } else {
                txtConfirmPassword.setStyle("-fx-border-color: red;");
                new Alert(Alert.AlertType.WARNING, "Check Pasword Again..").show();
                return;
            }

            if (id != -1L) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
            } else {
                new Alert(Alert.AlertType.CONFIRMATION, "something went wrong!").show();
            }

        }else {
            new Alert(Alert.AlertType.WARNING, "please fill all the fields correctly!").show();
        }
    }

    
    private void validateCustomer() {
        if (validate(txtFname, "[a-zA-Z]*")) {
            if (validate(txtMname, "[a-zA-Z]*")) {
                if (validate(txtLname, "[a-zA-Z]*")) {
                    if (validate(txtCity, "[a-zA-Z]*")) {
                        if (validate(txtAge, "\\d{1,}")) {
                            if (validate(txtEmail, "\\w+@\\w+\\.\\w+")) {
                                if (validate(txtUsername, "[a-zA-Z0-9_]{3,10}")) {
                                    if (validate(txtPassword, "[a-zA-Z0-9_]{3,16}")) {
                                        validate = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean validate(TextField textField, String regex){
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                textField.setStyle("-fx-border-color: #ff7373; -fx-border-width: 2px;");
            } else if (!newValue.matches(regex)) {
                textField.setStyle("-fx-border-color: #d90505; -fx-border-width: 2px;");
            } else if (newValue.matches(regex)) {
                textField.setStyle("-fx-border-color: #0fceeb; -fx-border-width: 2px;");
            }

        });
        return true;
    }


    public void btnCancelOnAction(ActionEvent actionEvent) {

    }
}
