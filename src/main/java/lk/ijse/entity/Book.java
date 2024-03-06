package lk.ijse.entity;

import lk.ijse.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Column(name = "book_title")
    private String title;
    @Column
    private String author;
    @Column
    private String genre;
    @Id
    @Column
    private long isbn;
    @Column(name = "publication_date")
    private LocalDate publicationDate;
    @Column(name = "availability_status")
    private String status;

    public BookDto toDto(){
        BookDto bookDto = new BookDto();
        bookDto.setTitle(this.title);
        bookDto.setAuthor(this.author);
        bookDto.setGenre(this.genre);
        bookDto.setIsbn(this.isbn);
        bookDto.setPublicationDate(this.publicationDate);
        bookDto.setStatus(this.status);
        return bookDto;
    }

}
