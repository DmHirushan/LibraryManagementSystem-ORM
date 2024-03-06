package lk.ijse.service;

import lk.ijse.entity.Admin;

public interface LoginService extends SuperService{
    public Long save(Admin admin);
    public boolean isAdminExists(String username);
}
