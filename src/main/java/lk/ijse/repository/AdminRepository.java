package lk.ijse.repository;

import lk.ijse.entity.Admin;
import lk.ijse.service.AdminService;

public interface AdminRepository extends CrudRepository <Admin, Long>{
    public Admin getUsingUsername(String username);
}
