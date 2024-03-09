package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.dto.CustomerDto;
import lk.ijse.embeded.NameIdentifier;
import lk.ijse.entity.Customer;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

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

    public void txtSignOnAction(ActionEvent actionEvent) {
        Long id = Long.valueOf(0);
        if (true) {
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

        }
    }

    private boolean validateCustomer() {
        if (Pattern.matches("[A-Z]\\w{3,}", txtFname.getText())){
            if (Pattern.matches("[A-Z]\\w{3,}", txtMname.getText())){
                if (Pattern.matches("[A-Z]\\w{3,}", txtLname.getText())){
                    if (Pattern.matches("[A-Z]\\w{3,}", txtCity.getText())){
                        if (Pattern.matches("\\d{1,}", txtAge.getText())){
                            if (Pattern.matches("\\w+@\\w+\\.\\w+", txtEmail.getText())){
                                if (Pattern.matches(" ", txtUsername.getText())){
                                    return true;
                                }
                            }
                        }
                    }
                }else {

                }
            }else {

            }
        }else {
            new Alert(Alert.AlertType.ERROR, "First Name invalid!").show();
            txtFname.setStyle("-fx-border-color: red; -fx-border-width: 5px");
        }
        return false;
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }
}
