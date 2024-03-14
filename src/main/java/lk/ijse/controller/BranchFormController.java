package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.BranchDto;
import lk.ijse.service.BranchService;
import lk.ijse.service.ServiceFactory;

import java.util.List;

public class BranchFormController {
    public TextField txtBranchName;
    public TextField txtCity;
    public TableView tblBranches;
    public TableColumn colBranchId;
    public TableColumn colBranchName;
    public TableColumn colCity;

    BranchService branchService = (BranchService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BRANCH);

    public void initialize(){
        setCellValueFactory();
        loadDataToTable();
    }



    private void setCellValueFactory() {
        colBranchId.setCellValueFactory(new PropertyValueFactory<>("branchId"));
        colBranchName.setCellValueFactory(new PropertyValueFactory<>("branchName"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
    }

    private void loadDataToTable() {
        ObservableList obList = FXCollections.observableArrayList();
        List<BranchDto> all =  branchService.getAll();
        for (BranchDto branchDto : all){
            obList.add(branchDto);
        }
        tblBranches.setItems(obList);
    }


    public void btnAddOnAction(ActionEvent actionEvent) {
        if (branchService.save(new BranchDto(
                txtBranchName.getText(),
                txtCity.getText()
        ))){
            new Alert(Alert.AlertType.CONFIRMATION, "Branch Added Successfully!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }
}
