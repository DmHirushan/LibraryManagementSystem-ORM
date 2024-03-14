package lk.ijse.service;

import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Customer;
import lk.ijse.projection.CustomerIds;

import java.util.List;

public interface CustomerService extends SuperService{
    public Long save(CustomerDto customerDto);
    public List<CustomerDto> getAll();
    public List<CustomerIds> getAllIds();
    public CustomerDto get(long id);
    public boolean update(CustomerDto customerDto);
    public void delete(CustomerDto customerDto);
    public boolean isExists(String username);
    public CustomerDto getCustomerUsingUsername(String username);
    public Long getCustomerCount();
}
