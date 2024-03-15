package lk.ijse.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dto.OrderDetailDto;
import lk.ijse.dto.OrdersDto;
import lk.ijse.service.OrderDetailService;
import lk.ijse.service.OrderService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.tmList.NotReturnedMembersTm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NotReturnedFormController {
    public TableView tblNotReturnedMembers;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colCity;
    public TableColumn colEmail;
    public TableColumn colBook;
    public TableColumn colDueDate;
    
    private OrderService orderService = (OrderService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER);
    OrderDetailService orderDetailService = (OrderDetailService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ORDER_DETAIL);

    public void initialize(){
        setCellValueFactory();
        loadNotReturnedMembers();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("memberId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        colBook.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
    }
    
    private void loadNotReturnedMembers(){
        List<OrdersDto> orders = orderService.getAll();
        List<Long> orderIds = new ArrayList<>();
        List<OrderDetailDto> orderDetailDtos = new ArrayList<>();

        ObservableList obList = FXCollections.observableArrayList();


        for (OrdersDto ordersDto : orders){
            if (ordersDto.getDueDate().isBefore(LocalDate.now()) && ordersDto.getReturnedDate() == null){

                NotReturnedMembersTm notReturnedMembersTm = new NotReturnedMembersTm();
                notReturnedMembersTm.setMemberId(ordersDto.getCustomer().getId());
                notReturnedMembersTm.setName(ordersDto.getCustomer().getName().getFirstName());
                notReturnedMembersTm.setCity(ordersDto.getCustomer().getCity());
                notReturnedMembersTm.setEMail(ordersDto.getCustomer().getEMail());
                notReturnedMembersTm.setDueDate(String.valueOf(ordersDto.getDueDate()));

                notReturnedMembersTm.setBookTitle(orderDetailService.get(ordersDto).getBook().getTitle());

                obList.add(notReturnedMembersTm);


            }
        }

        tblNotReturnedMembers.setItems(obList);
    }
}
