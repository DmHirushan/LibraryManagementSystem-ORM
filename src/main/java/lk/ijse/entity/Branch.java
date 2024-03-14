package lk.ijse.entity;

import lk.ijse.dto.BranchDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id")
    private long branchId;
    @Column(name = "branch_name")
    private String branchName;
    @Column
    private String city;

    public Branch(String branchName, String city) {
        this.branchName = branchName;
        this.city = city;
    }

    public BranchDto toDto(){
        BranchDto branchDto = new BranchDto();
        branchDto.setBranchId(branchId);
        branchDto.setBranchName(branchName);
        branchDto.setCity(city);
        return branchDto;
    }
}
