package lk.ijse.dto;

import lk.ijse.entity.Customer;
import lk.ijse.entity.Orders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class OrdersDto {
    private long orderId;
    private LocalDate date;
    private LocalDate dueDate;
    private Customer customer;

    public void toEntity(){
        Orders orders = new Orders();
        orders.setOrderId(orderId);
        orders.setDate(date);
        orders.setDueDate(dueDate);
        orders.setCustomer(customer);
    }
}
