package lk.ijse.repository;

import lk.ijse.entity.Book;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository <OrderDetail, Long>{
    public boolean saveOrderDetail(OrderDetail orderDetail);
    public List<OrderDetail> getOrderDetailList(Orders orders);
    public OrderDetail get(Orders orders);
    public List<OrderDetail> get(Book book);
}
