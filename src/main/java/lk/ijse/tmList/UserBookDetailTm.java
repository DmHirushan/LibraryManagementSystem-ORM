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
public class UserBookDetailTm {
    private int no;
    private String title;
    private long isbn;
    private LocalDate date;
    private LocalDate dueDate;
    private String returnedDate;
    private JFXButton button;
}
