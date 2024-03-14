package lk.ijse.entity;

import lk.ijse.dto.AdminDto;
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
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String username;
    @Column
    private String password;

    public AdminDto toDto(){
        AdminDto adminDto = new AdminDto();
        adminDto.setId(id);
        adminDto.setUsername(username);
        adminDto.setPassword(password);
        return adminDto;
    }
}
