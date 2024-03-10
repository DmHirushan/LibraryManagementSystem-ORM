package lk.ijse.dto;

import lk.ijse.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookDto implements Serializable {
    private String title;
    private String author;
    private String genre;
    private long isbn;
    private LocalDate publicationDate;
    private int qty;
    private byte[] imageData;

    public BookDto(String title, String author, String genre, int isbn, LocalDate publicationDate, int qty) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.qty = qty;
    }

    public Book toEntity(){
        Book book = new Book();
        book.setTitle(this.title);
        book.setAuthor(this.author);
        book.setGenre(this.genre);
        book.setIsbn(this.isbn);
        book.setPublicationDate(this.publicationDate);
        book.setQty(this.qty);
        book.setImageData(this.imageData);
        return book;
    }
}
