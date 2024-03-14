package lk.ijse.repository.impl;

import lk.ijse.entity.Admin;
import lk.ijse.repository.AdminRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AdminRepositoryImpl implements AdminRepository {
    private Session session;


    @Override
    public Long save(Admin object) {
        return null;
    }

    @Override
    public void update(Admin admin) {
    }

    @Override
    public Admin get(Long id) {
        return session.get(Admin.class, id);
    }

    @Override
    public void delete(Admin object) {

    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public Admin getUsingUsername(String username) {
        String sqlQuery = "FROM Admin  WHERE username = :username";
        Query query = session.createQuery(sqlQuery);
        query.setParameter("username", username);
        return (Admin) query.uniqueResult();
    }
}
