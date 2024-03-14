package lk.ijse.service;

import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Orders;
import lk.ijse.repository.CrudRepository;

import java.util.List;

public interface OrderService extends SuperService {
    public int getOrderId();
    public boolean save(Orders orders);
    public Long getBookCount(CustomerDto customerDto);
    public List<OrdersDto> getOrderByCusId(CustomerDto customerDto);
    public OrdersDto get(Long orderId);
    public boolean update(OrdersDto ordersDto);
    //public List<OrdersDto> getById(Long id);
}
