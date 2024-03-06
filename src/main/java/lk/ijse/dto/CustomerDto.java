package lk.ijse.dto;

import lk.ijse.embeded.NameIdentifier;
import lk.ijse.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerDto implements Serializable {
    private long id;
    private NameIdentifier name;
    private int age;
    private String city;
    private String eMail;
    private String username;
    private String password;

    public CustomerDto(NameIdentifier name, int age, String city, String eMail, String username, String password) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.eMail = eMail;
        this.username = username;
        this.password = password;
    }

    public Customer toEntity(){
        Customer customer = new Customer();
        customer.setId(this.id);
        customer.setName(this.name);
        customer.setAge(this.age);
        customer.setCity(this.city);
        customer.setEMail(this.eMail);
        customer.setUsername(this.username);
        customer.setPassword(this.password);
        return customer;
    }
}
