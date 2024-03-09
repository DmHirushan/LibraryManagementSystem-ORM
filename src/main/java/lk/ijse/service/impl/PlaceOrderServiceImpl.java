package lk.ijse.service.impl;

import lk.ijse.dto.BorrowBookDto;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.CustomerRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.PlaceOrderService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;

import java.util.List;

public class PlaceOrderServiceImpl implements PlaceOrderService {
    private Session session;

    CustomerRepository customerRepository = (CustomerRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.CUSTOMER);
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);
    @Override
    public List<BookIdsAndTitles> getIdsAndTitles() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookIdsAndTitles> idsAndTitles = bookRepository.getIdsAndTitles();
        return idsAndTitles;
    }

    @Override
    public boolean borowBooks(BorrowBookDto borrowBookDto) {
        return false;
    }
}
