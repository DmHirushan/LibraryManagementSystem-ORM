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
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;

import java.io.IOException;
import java.util.List;

public class libraryFormController {
    public TableColumn colTitle;

    public TableColumn colAuthor;
    public TableColumn colGenre;
    public TableColumn colIsbn;
    public TableColumn colPublicDate;
    public TableColumn colStatus;
    public TableView tblBooks;
    public TableColumn colQty;
    Stage stage = new Stage();
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        setCellValueFactory();
        loadAllBooks();
    }

    private void setCellValueFactory() {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colPublicDate.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
    }

    public void btnNewOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/addNewBookForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        tblBooks.refresh();
    }

    public void loadAllBooks(){
        ObservableList obList = FXCollections.observableArrayList();
        List<BookDto> dtoList = bookService.getAll();
        for (BookDto bookDto : dtoList){
            obList.add(bookDto);
        }
        tblBooks.setItems(obList);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/bookUpdateForm.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        btnUpdateOnAction(actionEvent);
    }
}
