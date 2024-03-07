package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;

import java.util.Arrays;
import java.util.Date;

public class AddNewBookFormController {
    public DatePicker datePicker;
    public TextField txtBookTitle;
    public TextField txtAuthor;
    public ComboBox cmbGenre;
    public TextField txtIsbn;
    public RadioButton rbAvailable;
    public RadioButton rbNotAvailable;
    public ComboBox cmbStatus;
    public ComboBox cmbQty;
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        loadBookGenresToGenreComboBox();
        setValuesToStatusComboBox();
    }
    public void btnAddOnAction(ActionEvent actionEvent) {
        bookService.save(new BookDto(
                txtBookTitle.getText(),
                txtAuthor.getText(),
                String.valueOf(cmbGenre.getValue()),
                Integer.parseInt(txtIsbn.getText()),
                datePicker.getValue(),
                Integer.parseInt(String.valueOf(cmbQty.getValue()))
        ));
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void loadBookGenresToGenreComboBox(){
        String [] genres = {"Biography", "Science", "Fantasy", "Romance", "Mystery", "Poetry", "Children's'", "Cookbooks", "Travel", "Historical-Fiction", "Self-Help"};
        ObservableList<String> obList = FXCollections.observableArrayList();
        for (String genre : genres){
            obList.add(genre);
        }
        cmbGenre.setItems(obList);
    }

    public void setValuesToStatusComboBox(){
        ObservableList<String> obList = FXCollections.observableArrayList();
        int [] qty = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        for (int x : qty){
            obList.add(String.valueOf(x));
        }
        cmbQty.setItems(obList);
    }
}
