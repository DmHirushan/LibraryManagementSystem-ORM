package lk.ijse.service;

import lk.ijse.entity.OrderDetail;
import lk.ijse.repository.CrudRepository;

public interface OrderDetailService extends SuperService {
    public boolean save(OrderDetail orderDetail);
}
