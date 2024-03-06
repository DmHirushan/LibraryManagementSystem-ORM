package lk.ijse.service.impl;

import lk.ijse.entity.Admin;
import lk.ijse.repository.LoginRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.service.LoginService;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class LoginServiceImpl implements LoginService {

    private Session session;
     LoginRepository loginRepository = (LoginRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.LOGIN);

    public Long save(Admin admin){
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            loginRepository.setSession(session);
            Long id = loginRepository.save(admin);
            transaction.commit();
            session.close();
            return id;
        } catch (Exception e) {
            transaction.rollback();
            session.close();
            e.printStackTrace();
            return -1L;
        }
    }
    @Override
    public boolean isAdminExists(String username) {
        return false;
    }
}
