package lk.ijse.repository;

import org.hibernate.Session;

public interface SuperRepository {
    public void setSession(Session session);
}
