package lk.ijse.repository.impl;

import lk.ijse.entity.Orders;
import lk.ijse.repository.OrderRepository;
import org.hibernate.Session;

public class OrderRepositoryImpl implements OrderRepository {
    private Session session;

    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public Long save(Orders orders) {
        return (Long) session.save(orders);
    }

    @Override
    public void update(Orders object) {

    }

    @Override
    public Orders get(Long aLong) {
        return null;
    }

    @Override
    public void delete(Orders object) {

    }
}
