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
public class ReturnDetailPrimaryKey implements Serializable {
    @Column(name = "return_id")
    private long returnId;
    @Column
    private long isbn;
}
