package lk.ijse.tmList;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CartTm {
    private int no;
    private String title;
    private String author;
    private LocalDate date;
    private String genre;
    private long isbn;
    private JFXButton button;
}
