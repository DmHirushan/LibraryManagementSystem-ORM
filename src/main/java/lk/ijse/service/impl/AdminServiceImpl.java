package lk.ijse.service.impl;

import lk.ijse.dto.AdminDto;
import lk.ijse.entity.Admin;
import lk.ijse.repository.AdminRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.AdminService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;

public class AdminServiceImpl implements AdminService {
    private Session session;
    AdminRepository adminRepository = (AdminRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.ADMIN);
    @Override
    public AdminDto get(long id) {
        return adminRepository.get(id).toDto();
    }

    @Override
    public AdminDto getUsingUsername(String username) {
        session = SessionFactoryConfig.getInstance().getSession();
        adminRepository.setSession(session);
        return adminRepository.getUsingUsername(username).toDto();
    }
}
