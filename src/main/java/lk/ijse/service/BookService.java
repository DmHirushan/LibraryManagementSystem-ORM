package lk.ijse.service;

import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.projection.BookIdsAndTitles;
import lk.ijse.projection.BookTitles;

import java.util.List;

public interface BookService extends SuperService{
    public Long save(BookDto bookDto);
    public List<BookDto> getAll();
    public boolean update(BookDto bookDto);
    public List<BookIdsAndTitles> getIdsAndTitles();
    public BookDto get(long id);
    public boolean delete(BookDto bookDto);
    public List<BookTitles> getAllTitles();
    public BookDto getIdByTitle(String title);
    public Long getBookCount();
}
