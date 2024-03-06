package lk.ijse.repository.impl;

import lk.ijse.entity.Admin;
import lk.ijse.repository.LoginRepository;
import org.hibernate.Session;

public class LoginRepositoryImpl implements LoginRepository {
    private Session session;

    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public Long save(Admin admin) {
        return null;
    }


    @Override
    public void update(Admin object) {

    }

    @Override
    public Admin get(Long aLong) {
        return null;
    }

    @Override
    public void delete(Admin object) {

    }
}
