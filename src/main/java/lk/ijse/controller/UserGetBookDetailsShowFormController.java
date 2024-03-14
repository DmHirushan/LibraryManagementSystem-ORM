package lk.ijse.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.BookDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.OrderDetailDto;
import lk.ijse.dto.OrdersDto;
import lk.ijse.service.*;
import lk.ijse.tmList.UserBookDetailTm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserGetBookDetailsShowFormController {
    public TableView tblBorrowedBookDetails;
    public TableColumn colNo;
    public TableColumn colTitle;
    public TableColumn colIsbn;
    public TableColumn colBorrowedDate;
    public TableColumn colDueDate;
    public TableColumn colReturnedDate;
    public TableColumn colOption;
    public TextField txtIsbn;
    private long cusId;

    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER);
    OrderDetailService orderDetailService = (OrderDetailService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);

    public void initialize() {
        setCellValueFactory();
        loadDataTotable();
        //System.out.println(UserDetailFormController.cusId);
        //cusId = UserDetailFormController.cusId;
    }

    private void setCellValueFactory() {
        colNo.setCellValueFactory(new PropertyValueFactory<>("no"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colBorrowedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colReturnedDate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
        colOption.setCellValueFactory(new PropertyValueFactory<>("button"));
    }

    private void loadDataTotable() {
        int x=0;
        int y=0;
        ObservableList obList = FXCollections.observableArrayList();
        List<UserBookDetailTm> userBookDetailTmList = new ArrayList<>();
        List<OrdersDto> orderByCusId = orderService.getOrderByCusId(customerService.get(UserDetailFormController.cusId));
        for (OrdersDto ordersDto : orderByCusId){
            UserBookDetailTm userBookDetailTm = new UserBookDetailTm();
            userBookDetailTm.setNo(++x);
            userBookDetailTm.setDate(ordersDto.getDate());
            userBookDetailTm.setDueDate(ordersDto.getDueDate());
            userBookDetailTm.setReturnedDate(ordersDto.getReturnedDate() == null ?"Not Returned Yet" : String.valueOf(ordersDto.getReturnedDate()));

            userBookDetailTmList.add(userBookDetailTm);

            List<OrderDetailDto> orderDetailDtos = new ArrayList<>();
            List<BookDto> bookDtos = new ArrayList<>();
            for (OrdersDto ordersDto1 : orderByCusId){
                OrderDetailDto orderDetailDto = orderDetailService.get(ordersDto1);
                orderDetailDtos.add(orderDetailDto);
                BookDto bookDto = bookService.get(orderDetailDto.getBook().getIsbn());
                bookDtos.add(bookDto);
            }


            userBookDetailTm.setIsbn(bookDtos.get(y).getIsbn());
            userBookDetailTm.setTitle(bookDtos.get(y).getTitle());
            y++;

            JFXButton button = new JFXButton("Return");
            button.setCursor(Cursor.HAND);
            button.setStyle("-fx-background-color: #11ff11; -fx-text-fill: black");
            userBookDetailTm.setButton(button);

            button.setOnAction(e -> {
                System.out.println("Button clicked!");
                // Add your action code here
                returnBook();
            });

            obList.add(userBookDetailTm);
        }
        tblBorrowedBookDetails.setItems(obList);
    }

    private void returnBook() {
    }

    public void btnReturnOnAction(ActionEvent actionEvent) {
        BookDto bookDto = bookService.get(Long.parseLong(txtIsbn.getText()));
        List<OrderDetailDto> dtoList = orderDetailService.get(bookDto);
        CustomerDto customerDto = customerService.get(UserDetailFormController.cusId);
        List<OrdersDto> orderByCusId = orderService.getOrderByCusId(customerDto);

        List<OrdersDto> orderList = new ArrayList<>();

        for (OrderDetailDto orderDetailDto : dtoList){
            orderList.add(orderService.get(orderDetailDto.getOrderDetailPrimaryKey().getOrderId()));
        }

        for (OrdersDto ordersDto : orderList){
            if (ordersDto.getCustomer().getId() == customerDto.getId()){
                ordersDto.setStatus("returned");
                ordersDto.setReturnedDate(LocalDate.now());
                orderService.update(ordersDto);
                bookDto.setQty(bookDto.getQty()+1);
                bookService.update(bookDto);
                tblBorrowedBookDetails.refresh();
                System.out.println("potha aragena");
                //System.out.println(LoginPageController.username);
                break;
            }else {
                System.out.println("potha aragena na meya");
                break;
            }
        }

    }
}
