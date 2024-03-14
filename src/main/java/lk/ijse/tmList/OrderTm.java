package lk.ijse.tmList;

import lk.ijse.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderTm {
    private long orderId;
    private String bookTitle;
    private String customer;
    private String  returnedDate;
    private long isbn;

}
