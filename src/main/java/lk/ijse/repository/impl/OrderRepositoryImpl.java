package lk.ijse.repository.impl;

import lk.ijse.entity.Orders;
import lk.ijse.repository.OrderRepository;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

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

    @Override
    public List getOrderId() {
        String hql = "SELECT orderId FROM Orders WHERE id = (SELECT MAX(id) FROM Orders)";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        return query.list();

    }
}
