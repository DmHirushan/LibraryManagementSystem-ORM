package lk.ijse.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column
    private String username;
    @Column
    private String password;
}
