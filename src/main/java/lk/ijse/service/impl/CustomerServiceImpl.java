package lk.ijse.service.impl;

import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;
import lk.ijse.projection.CustomerIds;
import lk.ijse.repository.CustomerRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.CustomerService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private Session session;
    CustomerRepository customerRepository = (CustomerRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.CUSTOMER);
    @Override
    public Long save(CustomerDto customerDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            customerRepository.setSession(session);
            Long id = customerRepository.save(customerDto.toEntity());
            transaction.commit();
            session.close();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1L;
        }
    }

    @Override
    public List<CustomerDto> getAll() {
        session = SessionFactoryConfig.getInstance().getSession();
        customerRepository.setSession(session);
        List<Customer> allCustomers = customerRepository.getAll();
        List<CustomerDto> dtoList = new ArrayList<>();
        for (Customer customer : allCustomers){
            dtoList.add(customer.toDto());
        }
        return dtoList;
    }

    @Override
    public List<CustomerIds> getAllIds() {
        session = SessionFactoryConfig.getInstance().getSession();
        customerRepository.setSession(session);
        return customerRepository.getAllIds();
    }

    @Override
    public CustomerDto get(long id) {
        session = SessionFactoryConfig.getInstance().getSession();
        customerRepository.setSession(session);
        CustomerDto customerDto = customerRepository.get(id).toDto();
        session.close();
        return customerDto;

    }

    @Override
    public boolean update(CustomerDto customerDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            customerRepository.setSession(session);
            customerRepository.update(customerDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public void delete(CustomerDto customerDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        customerRepository.setSession(session);
        Transaction transaction = session.beginTransaction();
        customerRepository.delete(customerDto.toEntity());
        transaction.commit();
        session.close();
    }

    @Override
    public boolean isExists(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        customerRepository.setSession(session);
        Long exists = customerRepository.isExists(username);
        return exists!=0;
    }

    @Override
    public CustomerDto getCustomerUsingUsername(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        customerRepository.setSession(session);
        Customer customer = customerRepository.getCustomerUsingUsername(username);
        session.close();
        return customer.toDto();
    }

    @Override
    public Long getCustomerCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        customerRepository.setSession(session);
        Long customerCount = customerRepository.getCustomerCount();
        session.close();
        return customerCount;
    }
}
