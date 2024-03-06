package lk.ijse.tmList;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.awt.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BookTm {
    private String title;
    private String author;
    private String genre;
    private long isbn;
    private LocalDate publicationDate;
    private String status;
    private JFXButton button;
}
