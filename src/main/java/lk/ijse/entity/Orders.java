package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "order_date_time")
    private LocalDate date;
    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "cus_id")
    private Customer customer;

    /*@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();*/

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "order")
    private List<OrderDetail> orderDetailList = new ArrayList<>();

    public Orders(long orderId, LocalDate date, LocalDate dueDate){
        this.orderId=orderId;
        this.date=date;
        this.dueDate=dueDate;
    }

    public Orders(LocalDate date, LocalDate dueDate){
        this.date=date;
        this.dueDate=dueDate;
    }
}
