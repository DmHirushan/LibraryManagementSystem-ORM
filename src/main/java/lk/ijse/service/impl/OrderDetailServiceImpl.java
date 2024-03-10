package lk.ijse.service.impl;

import lk.ijse.entity.OrderDetail;
import lk.ijse.repository.OrderDetailRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.OrderDetailService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OrderDetailServiceImpl implements OrderDetailService {
    private Session session;
    OrderDetailRepository orderDetailRepository = (OrderDetailRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER_DETAIL);
    @Override
    public boolean save(OrderDetail orderDetail) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderDetailRepository.setSession(session);
            orderDetailRepository.save(orderDetail);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }
    }
}
