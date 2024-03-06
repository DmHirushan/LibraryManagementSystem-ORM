package lk.ijse.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.dto.CustomerDto;
import lk.ijse.embeded.NameIdentifier;
import lk.ijse.entity.Customer;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

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
        NameIdentifier nameIdentifier = new NameIdentifier(
                txtFname.getText(),
                txtMname.getText(),
                txtLname.getText()
        );
        Long id = customerService.save(new CustomerDto(
                nameIdentifier,
                Integer.parseInt(txtAge.getText()),
                txtCity.getText(),
                txtEmail.getText(),
                txtUsername.getText(),
                txtPassword.getText()
        ));
        if (id!=-1L){
            new Alert(Alert.AlertType.CONFIRMATION, "Customer Saved!").show();
        }else {
            new Alert(Alert.AlertType.CONFIRMATION, "something went wrong!").show();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }
}
