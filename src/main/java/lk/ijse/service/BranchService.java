package lk.ijse.service;

import lk.ijse.dto.BranchDto;

import java.util.List;

public interface BranchService extends SuperService{
    public boolean save(BranchDto branchDto);
    public List<BranchDto> getAll();
    public boolean update(BranchDto branchDto);
    public boolean delete(BranchDto branchDto);
}
