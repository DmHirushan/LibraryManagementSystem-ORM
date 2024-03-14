package lk.ijse.repository;

import lk.ijse.entity.Customer;
import lk.ijse.projection.CustomerIds;
import org.hibernate.Session;

import java.util.List;

public interface CustomerRepository extends CrudRepository <Customer, Long> {
    public void setSession(Session session);
    public List<Customer> getAll();
    public List<CustomerIds> getAllIds();
    public Long isExists(String username);
    public Customer getCustomerUsingUsername(String username);
    public Long getCustomerCount();
}
