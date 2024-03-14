package lk.ijse.service.impl;

import lk.ijse.dto.BookDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.projection.BookTitles;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.BookService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {
    private Session session;
    BookRepository bookRepository = (BookRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BOOK);

    @Override
    public Long save(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            bookRepository.setSession(session);
            Long id = bookRepository.save(bookDto.toEntity());
            transaction.commit();
            session.close();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1L;
        }

    }

    @Override
    public List<BookDto> getAll() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<Book> allBooks = bookRepository.getAllBooks();
        List<BookDto> dtoList = new ArrayList<>();
        for (Book book : allBooks){
            dtoList.add(book.toDto());
        }
        return dtoList;
    }

    @Override
    public boolean update(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
           bookRepository.setSession(session);
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

    @Override
    public List<BookIdsAndTitles> getIdsAndTitles() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookIdsAndTitles> idsAndTitles = bookRepository.getIdsAndTitles();
        return idsAndTitles;
    }

    @Override
    public BookDto get(long id) {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        return bookRepository.get(id).toDto();
    }

    @Override
    public boolean delete(BookDto bookDto) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            bookRepository.setSession(session);
            bookRepository.delete(bookDto.toEntity());
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

    @Override
    public List<BookTitles> getAllTitles() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        List<BookTitles> titles = bookRepository.getTitles();
        session.close();
        return titles;
    }

    @Override
    public BookDto getIdByTitle(String title) {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        return bookRepository.get(bookRepository.getIdByTitle(title)).toDto();
    }

    @Override
    public Long getBookCount() {
        session = SessionFactoryConfig.getInstance().getSession();
        bookRepository.setSession(session);
        Long bookCount = bookRepository.getBookCount();
        session.close();
        return bookCount;
    }

}
