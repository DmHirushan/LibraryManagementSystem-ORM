package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import lk.ijse.dto.BookDto;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;

import java.util.Date;
import java.util.List;

public class BookUpdateFormController {
    public ComboBox cmbChooseBook;
    public TextField txtAuthor;
    public TextField txtGenre;
    public DatePicker datePicker;
    public ComboBox cmbStatus;
    public Label lblIsbn;

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize(){
        loadIdsAndTitles();
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean update = bookService.update(setValuesToDto(getBook()));
        if (update){
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Book Details Updated!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    private BookDto setValuesToDto(BookDto bookDto) {
        bookDto.setAuthor(txtAuthor.getText());
        bookDto.setGenre(txtGenre.getText());
        bookDto.setPublicationDate(datePicker.getValue());
        bookDto.setQty(Integer.parseInt((String) cmbStatus.getValue()));
        return bookDto;
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
    }

    public void cmbChooseBookOnAction(ActionEvent actionEvent) {
        setValuesToFields(getBook());
    }
    private BookDto getBook(){
        BookDto bookDto = bookService.get(split(String.valueOf(cmbChooseBook.getValue())));
        //System.out.println(bookDto);
        return bookDto;
    }

    void setValuesToFields(BookDto bookDto){
        txtAuthor.setText(bookDto.getAuthor());
        txtGenre.setText(bookDto.getGenre());
        lblIsbn.setText(String.valueOf(bookDto.getIsbn()));
        datePicker.setValue(bookDto.getPublicationDate());
        cmbStatus.setValue(bookDto.getQty());
    }
    private long split(String value){
        String[] parts = value.split(" ");
        System.out.println(parts[0]);
        return Long.parseLong(parts[0]);
    }

    public void loadIdsAndTitles(){
        List<BookIdsAndTitles> idsAndTitles = bookService.getIdsAndTitles();
        ObservableList obList = FXCollections.observableArrayList();
        for (BookIdsAndTitles bookIdsAndTitles : idsAndTitles){
            obList.add(bookIdsAndTitles);
        }
        cmbChooseBook.setItems(obList);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        if(bookService.delete(getBook())){
            clearFields();
            new Alert(Alert.AlertType.CONFIRMATION, "Book Deleted!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "something went wrong!").show();
        }
    }

    public void clearFields(){
        txtGenre.clear();
        txtAuthor.clear();
        lblIsbn.setText("");
        datePicker.setValue(null);
        cmbStatus.setValue(null);
    }
}
