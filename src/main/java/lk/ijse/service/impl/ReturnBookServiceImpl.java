package lk.ijse.service.impl;

import lk.ijse.dto.BookDto;
import lk.ijse.dto.OrdersDto;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.OrderRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.ReturnBookService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ReturnBookServiceImpl implements ReturnBookService {
    OrderRepository orderRepository = (OrderRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ORDER);
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    private Session session;

    @Override
    public boolean returnBook(OrdersDto ordersDto, BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            orderRepository.setSession(session);
            bookRepository.setSession(session);
            orderRepository.update(ordersDto.toEntity());
            bookRepository.update(bookDto.toEntity());
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return false;
        }

    }
}
