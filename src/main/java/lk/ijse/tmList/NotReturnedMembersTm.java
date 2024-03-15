package lk.ijse.tmList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class NotReturnedMembersTm {
    private long memberId;
    private String name;
    private String city;
    private String eMail;
    private String bookTitle;
    private String dueDate;
}
