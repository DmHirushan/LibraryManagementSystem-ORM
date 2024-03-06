package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.dto.CustomerDto;
import lk.ijse.embeded.NameIdentifier;
import lk.ijse.entity.Customer;
import lk.ijse.projection.CustomerIds;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

import java.util.List;

public class CustomerUpdateFormController {
    public TextField txtName;
    public TextField txtAge;
    public TextField txtCity;
    public TextField txtEmail;
    public TextField txtUsername;
    public AnchorPane customerUpdateContext;
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    public ComboBox cmbCusIds;

    public void initialize(){
        loadAllIds();
    }
    
    public void loadAllIds(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<CustomerIds> allIds = customerService.getAllIds();
        for (CustomerIds ids : allIds){
            obList.add(String.valueOf(ids.getId()));
        }
        cmbCusIds.setItems(obList);
    }

    public CustomerDto get(){
        String id = (String) cmbCusIds.getValue();
        CustomerDto customerDto = customerService.get(Long.valueOf(id));
        return customerDto;
    }

    public void cmbcusIdOnAction(ActionEvent actionEvent) {
        CustomerDto customerDto = get();
        setValues(customerDto);
    }

    private void setValues(CustomerDto customerDto) {
        txtName.setText(customerDto.getName().getFirstName()+ " " + customerDto.getName().getMiddleName() + " " + customerDto.getName().getLastName());
        txtAge.setText(String.valueOf(customerDto.getAge()));
        txtCity.setText(customerDto.getCity());
        txtEmail.setText(customerDto.getEMail());
        txtUsername.setText(customerDto.getUsername());
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        CustomerDto customerDto = customerService.get(Long.valueOf(Long.valueOf((String) cmbCusIds.getValue())));
        customerService.update(setNewValuesToEntity(customerDto));
        System.out.println(customerDto.getCity());
        //System.out.println("badu enwa!");
    }

    private CustomerDto setNewValuesToEntity(CustomerDto customerDto) {
        String [] parts = splitName(txtName.getText());
        customerDto.setName(new NameIdentifier(parts[0],parts[1],parts[2]));
        customerDto.setAge(Integer.parseInt(txtAge.getText()));
        customerDto.setCity(txtCity.getText());
        customerDto.setEMail(txtEmail.getText());
        customerDto.setUsername(txtUsername.getText());
        return customerDto;
    }

    private String[] splitName(String name){
        return name.split(" ");
    }

    public void btnCacelOnAction(ActionEvent actionEvent) {
        CustomerFormController.closeStage();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        customerService.delete(get());
    }
}
