package lk.ijse.service.impl;

import lk.ijse.entity.Orders;
import lk.ijse.repository.OrderRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.OrderService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private Session session;
    OrderRepository orderRepository  = (OrderRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
    @Override
    public int getOrderId() {
        session = SessionFactoryConfig.getInstance().getSession();
        orderRepository.setSession(session);
        List orderId = orderRepository.getOrderId();
        //System.out.println(orderId.get(0) == null ? 1 : Integer.parseInt(String.valueOf(orderId.get(0))));
        if (!orderId.isEmpty()){
            return Integer.parseInt(String.valueOf(orderId.get(0)))+1;
        }else {
            return -1;
        }


    }

    @Override
    public boolean save(Orders orders) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderRepository.setSession(session);
            session.save(orders);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            session.close();
            e.printStackTrace();
            return false;
        }
    }
}
