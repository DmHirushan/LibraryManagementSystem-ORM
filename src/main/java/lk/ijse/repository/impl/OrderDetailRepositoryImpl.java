package lk.ijse.repository.impl;

import lk.ijse.entity.OrderDetail;
import lk.ijse.repository.OrderDetailRepository;
import org.hibernate.Session;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    private Session session;

    public void setSession(Session session){
        this.session = session;
    }
    @Override
    public Long save(OrderDetail orderDetail) {
        return null;
    }

    @Override
    public void update(OrderDetail object) {

    }

    @Override
    public OrderDetail get(Long aLong) {
        return null;
    }

    @Override
    public void delete(OrderDetail object) {

    }

    @Override
    public boolean saveOrderDetail(OrderDetail orderDetail) {
        session.save(orderDetail);
        return true;
    }
}
