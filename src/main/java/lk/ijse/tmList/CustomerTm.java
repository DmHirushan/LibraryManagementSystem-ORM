package lk.ijse.tmList;

import lk.ijse.embeded.NameIdentifier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomerTm {
    private long id;
    private String name;
    private int age;
    private String city;
    private String eMail;
}
