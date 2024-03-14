package lk.ijse.service.impl;

import lk.ijse.dto.BookDto;
import lk.ijse.dto.OrderDetailDto;
import lk.ijse.dto.OrdersDto;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.repository.OrderDetailRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.OrderDetailService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<OrderDetailDto> getOrderDetailList(OrdersDto ordersDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        orderDetailRepository.setSession(session);
        List<OrderDetail> orderDetailList = orderDetailRepository.getOrderDetailList(ordersDto.toEntity());
        List<OrderDetailDto> dtoList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetailList){
            dtoList.add(orderDetail.toDto());
        }
        return dtoList;
    }

    @Override
    public OrderDetailDto get(OrdersDto ordersDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        orderDetailRepository.setSession(session);
        return orderDetailRepository.get(ordersDto.toEntity()).toDto();
    }

    @Override
    public List<OrderDetailDto>  get(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        orderDetailRepository.setSession(session);
        List<OrderDetail> orderDetails = orderDetailRepository.get(bookDto.toEntity());
        List<OrderDetailDto> dtoList = new ArrayList<>();
        for (OrderDetail orderDetail : orderDetails){
            dtoList.add(orderDetail.toDto());
        }
        return dtoList;
    }
}
