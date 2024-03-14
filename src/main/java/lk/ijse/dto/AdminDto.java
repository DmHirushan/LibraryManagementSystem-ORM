package lk.ijse.dto;

import lk.ijse.entity.Admin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AdminDto {
    private long id;
    private String username;
    private String password;

    public Admin toEntity(){
        Admin admin = new Admin();
        admin.setId(id);
        admin.setUsername(username);
        admin.setPassword(password);
        return admin;
    }
}
