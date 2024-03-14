package lk.ijse.entity;

import lk.ijse.dto.OrderDetailDto;
import lk.ijse.embeded.OrderDetailPrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailPrimaryKey orderDetailPrimaryKey;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", insertable = false, updatable = false)
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "isbn", referencedColumnName = "isbn", insertable = false, updatable = false)
    private Book book;

    public OrderDetailDto toDto(){
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setOrderDetailPrimaryKey(orderDetailPrimaryKey);
        orderDetailDto.setBook(book);
        return orderDetailDto;
    }
}
