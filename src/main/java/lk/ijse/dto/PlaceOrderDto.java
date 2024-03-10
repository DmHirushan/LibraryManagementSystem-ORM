package lk.ijse.dto;

import lk.ijse.tmList.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class PlaceOrderDto {
    private long orderId;
    private long cusId;
    private long isbn;
    //private LocalDateTime orderDateTime;
    private LocalDate dueDate;
    //private List<CartTm> tmList;
}