package lk.ijse.dto;

import lk.ijse.embeded.OrderDetailPrimaryKey;
import lk.ijse.entity.Book;
import lk.ijse.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrderDetailDto {
    private OrderDetailPrimaryKey orderDetailPrimaryKey;
    private Book book;
}
