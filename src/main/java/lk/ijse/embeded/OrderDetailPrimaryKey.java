package lk.ijse.embeded;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Embeddable
public class OrderDetailPrimaryKey implements Serializable {
    @Column(name = "order_id")
    private long orderId;
    @Column
    private long isbn;
}
