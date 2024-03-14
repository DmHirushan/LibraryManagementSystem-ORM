package lk.ijse.repository.impl;

import lk.ijse.entity.Book;
import lk.ijse.entity.Customer;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.repository.OrderDetailRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    @Override
    public List<OrderDetail> getOrderDetailList(Orders orders) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> criteria = builder.createQuery(OrderDetail.class);
        Root<OrderDetail> root = criteria.from(OrderDetail.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("order"), orders));

        List<OrderDetail> resultList = session.createQuery(criteria).getResultList();
        return resultList;
    }

    @Override
    public OrderDetail get(Orders orders) {
        String hql = "FROM OrderDetail WHERE order = :order";
        Query<OrderDetail> query = session.createQuery(hql, OrderDetail.class);
        query.setParameter("order", orders);
        return query.uniqueResult();
    }

    @Override
    public List<OrderDetail> get(Book book) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<OrderDetail> criteria = builder.createQuery(OrderDetail.class);
        Root<OrderDetail> root = criteria.from(OrderDetail.class);

        criteria.select(root);
        criteria.where(builder.equal(root.get("book"), book));

        List<OrderDetail> resultList = session.createQuery(criteria).getResultList();
        return resultList;
    }

}
