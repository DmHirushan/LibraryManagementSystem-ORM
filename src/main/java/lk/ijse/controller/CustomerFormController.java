package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tmList.CustomerTm;

import java.io.IOException;
import java.util.List;

public class CustomerFormController {
    public TableView tblCustomer;
    public TableColumn colCustomerNo;
    public TableColumn colName;
    public TableColumn colAge;
    public TableColumn colCity;
    public TableColumn colEmail;
    static Stage stage = new Stage();
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);

    public void initialize(){
        setCellValueFactory();
        getAllCustomers();
    }

    private void setCellValueFactory() {
        colCustomerNo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("eMail"));
    }
    
    public void getAllCustomers(){
        List<CustomerDto> allCustomers = customerService.getAll();
        ObservableList<CustomerTm> oblist = FXCollections.observableArrayList();
        for (CustomerDto customerDto : allCustomers){
            //String fName = customer.getName().getFirstName();
            oblist.add(
                new CustomerTm(
                        customerDto.getId(),
                        customerDto.getName().getFirstName()+" "+customerDto.getName().getMiddleName(),
                        customerDto.getAge(),
                        customerDto.getCity(),
                        customerDto.getEMail()
                )
            );
        }
        tblCustomer.setItems(oblist);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/customerUpdateForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void closeStage(){
        stage.close();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
