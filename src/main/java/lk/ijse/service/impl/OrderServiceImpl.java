package lk.ijse.service.impl;

import lk.ijse.dto.CustomerDto;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.Customer;
import lk.ijse.entity.Orders;
import lk.ijse.repository.OrderRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.OrderService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private Session session;
    OrderRepository orderRepository  = (OrderRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
    @Override
    public int getOrderId() {
        session = SessionFactoryConfig.getInstance().getSession();
        orderRepository.setSession(session);
        List orderId = orderRepository.getOrderId();
        session.close();
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

    @Override
    public Long getBookCount(CustomerDto customerDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        orderRepository.setSession(session);
        Long bookCount = orderRepository.getBookCount(customerDto.toEntity());
        session.close();
        return bookCount;
    }

    @Override
    public List<OrdersDto> getOrderByCusId(CustomerDto customerDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        orderRepository.setSession(session);
        List<Orders> orderByCusId = orderRepository.getOrderByCusId(customerDto.toEntity());
        List<OrdersDto> dtoList = new ArrayList<>();
        for (Orders order : orderByCusId){
            dtoList.add(order.toDto());
        }
        session.close();
        return dtoList;
    }

    @Override
    public OrdersDto get(Long orderId) {
        session = SessionFactoryConfig.getInstance().getSession();
        orderRepository.setSession(session);
        return orderRepository.get(orderId).toDto();
    }

    @Override
    public boolean update(OrdersDto ordersDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderRepository.setSession(session);
            orderRepository.update(ordersDto.toEntity());
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

    /*@Override
    public List<OrdersDto> getById(Long id) {
        session = SessionFactoryConfig.getInstance().getSession();
        orderRepository.setSession(session);
        return orderRepository.getById(id);

    }*/
}
