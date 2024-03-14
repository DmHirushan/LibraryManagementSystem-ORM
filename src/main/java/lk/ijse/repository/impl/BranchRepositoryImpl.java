package lk.ijse.repository.impl;

import lk.ijse.entity.Branch;
import lk.ijse.repository.BranchRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BranchRepositoryImpl implements BranchRepository {
    private Session session;

    @Override
    public Long save(Branch branch) {
        return (Long) session.save(branch);
    }

    @Override
    public void update(Branch branch) {
        session.update(branch);
    }

    @Override
    public Branch get(Long aLong) {
        return null;
    }

    @Override
    public void delete(Branch branch) {
        session.delete(branch);
    }

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Branch> getAll() {
        String sqlQuery = "FROM Branch ";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }
}
