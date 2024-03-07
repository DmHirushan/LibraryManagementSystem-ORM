package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Cursor;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.BookDto;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tmList.BookTm;

import java.util.ArrayList;
import java.util.List;

public class UserLibraryFormController {

    public TableView tblBooks;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colPublicationDate;
    public TableColumn colGenre;
    public TableColumn colIsbn;
    public TableColumn colOption;
    public TableColumn colStatus;
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
        colPublicationDate.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));

    }

    void loadAllBooks(){
        ObservableList obList = FXCollections.observableArrayList();
        List<BookDto> allBooks = bookService.getAll();
        for (BookDto bookDto : allBooks){
            JFXButton purchase = new JFXButton("Purchase");
            purchase.setStyle("-fx-background-color: #5ee65e; -fx-text-fill: white; ");
            purchase.setCursor(Cursor.HAND);
            if (bookDto.getQty()==0){
                purchase.setOpacity(0.3);
            }
            obList.add(new BookTm(
                    bookDto.getTitle(),
                    bookDto.getAuthor(),
                    bookDto.getGenre(),
                    bookDto.getIsbn(),
                    bookDto.getPublicationDate(),
                    bookDto.getQty()!=0 ? "Available" : "Not Available",
                    purchase
            ));
        }
        tblBooks.setItems(obList);
    }
}
