package lk.ijse.repository.impl;

import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.repository.BookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    private Session session;
    public void setSession(Session session){
        this.session = session;
    }

    @Override
    public List<Book> getAllBooks() {
        String sqlQuery = "FROM Book";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public List<BookIdsAndTitles> getIdsAndTitles() {
        String sqlQuery = "SELECT new lk.ijse.projection.BookIdsAndTitles (B.isbn, B.title) FROM Book AS B";
        Query query = session.createQuery(sqlQuery);
        List list = query.list();
        session.close();
        return list;
    }

    @Override
    public Long save(Book book) {
        return (Long) session.save(book);

    }

    @Override
    public void update(Book book) {
        session.update(book);
    }

    @Override
    public Book get(Long id) {
        return session.get(Book.class, id);
    }

    @Override
    public void delete(Book book) {
        session.delete(book);
    }
}
