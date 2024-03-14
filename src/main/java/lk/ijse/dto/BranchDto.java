package lk.ijse.dto;

import lk.ijse.entity.Branch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class BranchDto {
    private long branchId;
    private String branchName;
    private String city;

    public BranchDto(String branchName, String city) {
        this.branchName = branchName;
        this.city = city;
    }

    public Branch toEntity(){
        Branch branch = new Branch();
        branch.setBranchId(branchId);
        branch.setBranchName(branchName);
        branch.setCity(city);
        return branch;
    }
}
