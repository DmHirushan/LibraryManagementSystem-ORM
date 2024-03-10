package lk.ijse.service;

import lk.ijse.entity.Orders;
import lk.ijse.repository.CrudRepository;

public interface OrderService extends SuperService {
    public int getOrderId();
    public boolean save(Orders orders);
}
