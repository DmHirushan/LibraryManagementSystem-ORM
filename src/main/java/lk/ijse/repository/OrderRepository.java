package lk.ijse.repository;

import lk.ijse.entity.Customer;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;

import java.util.List;

public interface OrderRepository extends CrudRepository <Orders, Long>{
    public List getOrderId();
    public Long getBookCount(Customer customer);
    public List<Orders> getOrderByCusId(Customer customer);
    public List<Orders> getById(Long id);
}
