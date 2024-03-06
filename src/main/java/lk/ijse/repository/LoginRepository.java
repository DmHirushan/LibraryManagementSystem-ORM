package lk.ijse.repository;

import lk.ijse.entity.Admin;
import org.hibernate.Session;

public interface LoginRepository extends CrudRepository <Admin, Long>{
    public void setSession(Session session);
    public Long save(Admin admin);
}
