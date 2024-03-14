package lk.ijse.service;

import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;

public interface AdminService extends SuperService{
    public AdminDto get(long id);
    public AdminDto getUsingUsername(String username);
}
