package lk.ijse.repository.impl;

import lk.ijse.entity.Customer;
import lk.ijse.projection.CustomerIds;
import lk.ijse.repository.CustomerRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepository {
    private Session session;
    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public List<Customer> getAll() {
        String sqlQuery = "FROM Customer";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<CustomerIds> getAllIds() {
        String sqlQuery = "SELECT new lk.ijse.projection.CustomerIds(C.id) FROM Customer AS C";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public Long isExists(String username) {
        String hql = "SELECT COUNT(*) FROM Customer WHERE username = :username";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("username", username);

        // Execute query
        return query.uniqueResult();
    }

    @Override
    public Customer getCustomerUsingUsername(String username) {
        String hql = "FROM Customer WHERE username = :username";
        Query<Customer> query = session.createQuery(hql, Customer.class);
        query.setParameter("username", username);
        return query.uniqueResult();
    }

    @Override
    public Long getCustomerCount() {
        Query query = session.createQuery("select count(*) from Customer ");
        return (Long) query.uniqueResult();
    }

    @Override
    public Long save(Customer customer) {
        return (Long) session.save(customer);
    }


    @Override
    public void update(Customer customer) {
        session.update(customer);
    }

    @Override
    public Customer get(Long id) {
        return session.get(Customer.class, id);
    }

    @Override
    public void delete(Customer customer) {
        session.delete(customer);
    }
}
