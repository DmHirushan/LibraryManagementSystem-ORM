package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.service.BookService;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tmList.CartTm;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class UserDetailFormController {
    public Label lblName;
    public Label lblCount;
    public ComboBox cmbSearchBooks;
    public Label lblTitle;
    public Label lblAuthor;
    public Label lblGenre;
    public Label lblPublicationDate;
    public Label lblIsbn;
    public TableView tblCart;
    public TableColumn colNo;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colPublicationDate;
    public TableColumn colGenre;
    public TableColumn colIsbn;
    public TableColumn colOption;
    private int x=0;
    private ObservableList obList = FXCollections.observableArrayList();

    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        loadIdsAndTitles();
        setName(LoginPageController.username);
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPublicationDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    private void setName(String username) {
        CustomerDto customerDto = customerService.getCustomerUsingUsername(username);
        lblName.setText(customerDto.getName().getFirstName()+" "+customerDto.getName().getMiddleName()+" "+customerDto.getName().getLastName());
    }

    public void cmbSearchBooksOnAction(ActionEvent actionEvent) {
        setValuesToFields(bookService.get(split(String.valueOf(cmbSearchBooks.getValue()))));
    }

    public long split(String value){
        String[] parts = value.split(" ");
        return Long.parseLong(parts[0]);
    }

    public void setValuesToFields(BookDto bookDto){
        lblTitle.setText(bookDto.getTitle());
        lblAuthor.setText(bookDto.getAuthor());
        lblGenre.setText(bookDto.getGenre());
        lblPublicationDate.setText(String.valueOf(bookDto.getPublicationDate()));
        lblIsbn.setText(String.valueOf(bookDto.getIsbn()));
    }

    public void loadIdsAndTitles(){
        List<BookIdsAndTitles> idsAndTitles = bookService.getIdsAndTitles();
        ObservableList obList = FXCollections.observableArrayList();
        for (BookIdsAndTitles bookIdsAndTitles : idsAndTitles){
            obList.add(bookIdsAndTitles);
        }
        cmbSearchBooks.setItems(obList);
    }

    public void btnAddToCartOnAction(ActionEvent actionEvent) {
        JFXButton remove = new JFXButton("Remove");
        remove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int index = tblCart.getSelectionModel().getSelectedIndex();
                obList.remove(index);
                tblCart.refresh();
            }
        });

        for (int i = 0; i < tblCart.getItems().size(); i++) {
            if (lblIsbn.equals(colIsbn.getCellData(i))) {
                new Alert(Alert.AlertType.WARNING, "You can't borrow same book twice..").show();
                return;
            }
        }


        obList.add(new CartTm(
                ++x,
                lblTitle.getText(),
                lblAuthor.getText(),
                LocalDate.parse(lblPublicationDate.getText()),
                lblGenre.getText(),
                Integer.parseInt(lblIsbn.getText()),
                remove
        ));
        tblCart.setItems(obList);
    }

    public void btnGetOnAction(ActionEvent actionEvent) {
    }
}
