package lk.ijse.entity;

import lk.ijse.embeded.ReturnDetailPrimaryKey;
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
@Table(name = "return_detail")
public class ReturnDetail {
    @EmbeddedId
    private ReturnDetailPrimaryKey returnDetailPrimaryKey;

    @ManyToOne
    @JoinColumn(name = "return_id", referencedColumnName = "return_id", insertable = false, updatable = false)
    private Returns returns;

    @ManyToOne
    @JoinColumn(name = "isbn", referencedColumnName = "isbn", insertable = false, updatable = false)
    private Book book;

}
