package lk.ijse.service.impl;

import lk.ijse.dto.BorrowBookDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.embeded.OrderDetailPrimaryKey;
import lk.ijse.entity.Book;
import lk.ijse.entity.OrderDetail;
import lk.ijse.entity.Orders;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.repository.*;
import lk.ijse.service.PlaceOrderService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private Session session;

    CustomerRepository customerRepository = (CustomerRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.CUSTOMER);
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    OrderRepository orderRepository = (OrderRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
    OrderDetailRepository orderDetailRepository = (OrderDetailRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER_DETAIL);
    @Override
    public List<BookIdsAndTitles> getIdsAndTitles() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookIdsAndTitles> idsAndTitles = bookRepository.getIdsAndTitles();
        return idsAndTitles;
    }

    @Override
    public boolean placeOrder(PlaceOrderDto placeOrderDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        customerRepository.setSession(session);
        Orders orders = new Orders(placeOrderDto.getOrderId(), placeOrderDto.getDueDate(), customerRepository.get(placeOrderDto.getCusId()), null, null);

        bookRepository.setSession(session);
        Book book = bookRepository.get(placeOrderDto.getIsbn());

        orderRepository.setSession(session);
        Long saveOrder = orderRepository.save(orders);

        orderDetailRepository.setSession(session);
        boolean savedOrderDetail = orderDetailRepository.saveOrderDetail(new OrderDetail(new OrderDetailPrimaryKey(placeOrderDto.getOrderId(), placeOrderDto.getIsbn()), orders, book));

        book.setQty(book.getQty()-1);
        bookRepository.update(book);

        if (saveOrder > 0){
            if (savedOrderDetail){
                transaction.commit();
                return true;
            }
        }else
            transaction.rollback();
            session.close();
            return false;
    }


}
