package lk.ijse.repository.impl;

import lk.ijse.entity.Customer;
import lk.ijse.repository.QueryRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class QueryRepositoryImpl implements QueryRepository {
    private Session session;
    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public List<Customer> getNotRetunedMembers() {
        /*String hql = "SELECT a, b FROM EntityA a JOIN a.entityB b";
        Query query = session.createQuery(hql);
        List<Object[]> results = query.list();
        for (Object[] result : results) {
            EntityA entityA = (EntityA) result[0];
            EntityB entityB = (EntityB) result[1];
            // Process the entities as needed
        }*/
        return null;
    }
}
