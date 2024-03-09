package lk.ijse.repository;

import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookIdsAndTitles;
import org.hibernate.Session;

import java.util.List;

public interface BookRepository extends CrudRepository <Book, Long>{
    public void setSession(Session session);
    public List<Book> getAllBooks();
    public List<BookIdsAndTitles> getIdsAndTitles();
    public void decreaseBook();
}
