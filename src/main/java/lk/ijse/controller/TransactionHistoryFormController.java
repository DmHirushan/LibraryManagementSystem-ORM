package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import lk.ijse.tmList.OrderTm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionHistoryFormController {
    public TableView tblHistory;
    public TableColumn colOrderId;
    public TableColumn colBook;
    public TableColumn colCustomer;
    public TableColumn colStatus;
    public TableColumn colReturnedDate;
    public TableColumn colIsbn;
    public TextField txtIsbn;
    public TextField txtOrderId;

    OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER);
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);
    OrderDetailService orderDetailService = (OrderDetailService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);
    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);
    ReturnBookService returnBookService = (ReturnBookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.RETURN_BOOK);


    public void initialize(){
        setCellValueFactory();
        loadAllOrders();
    }

    private void setCellValueFactory() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colBook.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colCustomer.setCellValueFactory(new PropertyValueFactory<>("customer"));
        colReturnedDate.setCellValueFactory(new PropertyValueFactory<>("returnedDate"));
        colIsbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
    }

    public void loadAllOrders(){
        ObservableList obList = FXCollections.observableArrayList();

        List<OrdersDto> all = orderService.getAll();
        List<CustomerDto> customers = new ArrayList<>();
        List<OrderDetailDto> orderDetails = orderDetailService.getAll();
        List<BookDto> books = new ArrayList<>();

        for (OrdersDto ordersDto : all){
            customers.add(customerService.get(ordersDto.getCustomer().getId()));
        }

        for (OrderDetailDto orderDetailDto : orderDetails){
            books.add(bookService.get(orderDetailDto.getBook().getIsbn()));
        }

        for (int i=0; i<all.size(); i++){
            OrderTm orderTm = new OrderTm();
            orderTm.setOrderId(orderDetails.get(i).getOrderDetailPrimaryKey().getOrderId());
            orderTm.setReturnedDate(all.get(i).getReturnedDate() == null ? "Not Returned Yet" : String.valueOf(all.get(i).getReturnedDate()));
            orderTm.setCustomer(customers.get(i).getName().getFirstName()+" "+customers.get(i).getName().getMiddleName());
            orderTm.setBookTitle(books.get(i).getTitle());
            orderTm.setIsbn(books.get(i).getIsbn());
            obList.add(orderTm);
        }

        tblHistory.setItems(obList);

    }
    public void btnReturnOnAction(ActionEvent actionEvent) {
        OrdersDto ordersDto = orderService.get(Long.valueOf(txtOrderId.getText()));
        ordersDto.setReturnedDate(LocalDate.now());
        ordersDto.setStatus("returned");
        OrderDetailDto orderDetailDto = orderDetailService.get(ordersDto);
        BookDto bookDto = bookService.get(orderDetailDto.getBook().getIsbn());
        bookDto.setQty(bookDto.getQty()+1);
        orderService.update(ordersDto);
        bookService.update(bookDto);
        if (returnBookService.returnBook(ordersDto, bookDto)){
            new Alert(Alert.AlertType.CONFIRMATION, "Book returned!").show();
        }else {
            new Alert(Alert.AlertType.ERROR, "something went wrong").show();
        }



        /*BookDto bookDto = bookService.get(Long.parseLong(txtIsbn.getText()));
        List<OrderDetailDto> dtoList = orderDetailService.get(bookDto);
        CustomerDto customerDto = customerService.get(UserDetailFormController.cusId);
        List<OrdersDto> orderByCusId = orderService.getOrderByCusId(customerDto);

        List<OrdersDto> orderList = new ArrayList<>();

        for (OrderDetailDto orderDetailDto : dtoList){
            orderList.add(orderService.get(orderDetailDto.getOrderDetailPrimaryKey().getOrderId()));
        }

        for (OrdersDto ordersDto : orderList) {
            if (ordersDto.getCustomer().getId() == customerDto.getId()) {
                ordersDto.setStatus("returned");
                ordersDto.setReturnedDate(LocalDate.now());
                orderService.update(ordersDto);
                bookDto.setQty(bookDto.getQty() + 1);
                bookService.update(bookDto);
                tblHistory.refresh();
                System.out.println("potha aragena");
                //System.out.println(LoginPageController.username);
                break;
            } else {
                System.out.println("potha aragena na meya");
                break;
            }*/

    }
}
