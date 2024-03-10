package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.service.*;
import lk.ijse.tmList.CartTm;

import java.io.ByteArrayInputStream;
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
    public DatePicker returnDatePicker;
    public Label lblOrderId;
    public ImageView bookImageView;
    private int x=0;
    private ObservableList obList = FXCollections.observableArrayList();
    CustomerDto customerDto;

    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);
    OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER);
    PlaceOrderService placeOrderService = (PlaceOrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.PLACE_ORDER);

    public void initialize(){
        loadIdsAndTitles();
        setName(LoginPageController.username);
        //setCellValueFactory();
        setOrderId();
        setStyleToImageView();
        setStyleToImageView();
    }

    private void setStyleToImageView() {
//        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(2), bookImageView);
//        rotateTransition.setByAngle(360); // Rotate 360 degrees
//        rotateTransition.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
//        rotateTransition.play();

//        Timeline timeline = new Timeline(
//                new KeyFrame(Duration.ZERO, new KeyValue(bookImageView.rotateProperty(), 0)),
//                new KeyFrame(Duration.seconds(5), new KeyValue(bookImageView.rotateProperty(), 360))
//        );
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
    }

    private void setOrderId() {
        if (orderService.getOrderId() != -1){
            lblOrderId.setText(String.valueOf(orderService.getOrderId()));
        }else {
            lblOrderId.setText("1");
        }

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
        customerDto = customerService.getCustomerUsingUsername(username);
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
        byte[] imageData = bookDto.getImageData();
        //System.out.println("null");
        Image image = new Image(new ByteArrayInputStream(imageData));

//        bookImageView.setRotate(0); // Ensure the initial rotation is 0
//        bookImageView.setTranslateX(image.getWidth() / 2);
//        bookImageView.setTranslateY(image.getHeight() / 2);

//        bookImageView.setFitWidth(200);
//        bookImageView.setFitHeight(200);
//        bookImageView.setRotate(0); // Ensure the initial rotation is 0
//        bookImageView.setP(bookImageView.getFitWidth() / 2);
//        bookImageView.setPivotY(bookImageView.getFitHeight() / 2);

        bookImageView.setImage(image);
    }

    public void loadIdsAndTitles(){
        List<BookIdsAndTitles> idsAndTitles = bookService.getIdsAndTitles();
        ObservableList obList = FXCollections.observableArrayList();
        for (BookIdsAndTitles bookIdsAndTitles : idsAndTitles){
            obList.add(bookIdsAndTitles);
        }
        cmbSearchBooks.setItems(obList);
    }

    /*public void btnAddToCartOnAction(ActionEvent actionEvent) {
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
    }*/

    public void btnGetOnAction(ActionEvent actionEvent) {
        PlaceOrderDto placeOrderDto = new PlaceOrderDto(
                Long.parseLong(lblOrderId.getText()),
                customerDto.getId(),
                Long.parseLong(lblIsbn.getText()),
                returnDatePicker.getValue()
        );
        if (placeOrderService.placeOrder(placeOrderDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "You Get it Successfully!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "something went wrong").show();
        }

    }
}
