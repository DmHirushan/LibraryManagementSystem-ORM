package lk.ijse.repository;

import lk.ijse.entity.Branch;

import java.util.List;

public interface BranchRepository extends CrudRepository <Branch, Long>{
    public List<Branch> getAll();
}
