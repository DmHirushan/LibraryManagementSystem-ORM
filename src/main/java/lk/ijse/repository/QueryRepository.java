package lk.ijse.repository;

import lk.ijse.entity.Customer;

import java.util.List;

public interface QueryRepository extends SuperRepository{
    public List<Customer> getNotRetunedMembers();
}
