package lk.ijse.repository;

import lk.ijse.entity.Orders;

import java.util.List;

public interface OrderRepository extends CrudRepository <Orders, Long>{
    public List getOrderId();

}
