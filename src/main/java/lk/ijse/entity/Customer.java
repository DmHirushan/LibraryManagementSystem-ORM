package lk.ijse.entity;

import lk.ijse.dto.CustomerDto;
import lk.ijse.embeded.NameIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "customer")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cus_id")
    private long id;
    @Column
    private NameIdentifier name;
    @Column
    private int age;
    @Column
    private String city;
    @Column(name = "e_mail")
    private String eMail;
    @Column
    private String username;
    @Column
    private String password;

    public Customer(NameIdentifier name, int age, String city, String eMail, String username, String password) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.eMail = eMail;
        this.username = username;
        this.password = password;
    }

    public CustomerDto toDto(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(this.id);
        customerDto.setName(this.name);
        customerDto.setAge(this.age);
        customerDto.setCity(this.city);
        customerDto.setEMail(this.eMail);
        customerDto.setUsername(this.username);
        customerDto.setPassword(this.password);
        return customerDto;
    }
}
