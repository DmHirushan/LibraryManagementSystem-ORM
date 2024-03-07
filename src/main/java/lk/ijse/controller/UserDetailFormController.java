package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.service.BookService;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

import java.util.List;

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
    private ObservableList obList = FXCollections.observableArrayList();

    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        loadIdsAndTitles();
        setName(LoginPageController.username);
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

    }
}
