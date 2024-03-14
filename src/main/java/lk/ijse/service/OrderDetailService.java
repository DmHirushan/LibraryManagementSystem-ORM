package lk.ijse.service;

import lk.ijse.dto.BookDto;
import lk.ijse.dto.OrderDetailDto;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.repository.CrudRepository;

import java.util.List;

public interface OrderDetailService extends SuperService {
    public boolean save(OrderDetail orderDetail);
    public List<OrderDetailDto> getOrderDetailList(OrdersDto ordersDto);
    public OrderDetailDto get(OrdersDto ordersDto);
    public List<OrderDetailDto> get(BookDto bookDto);
}
