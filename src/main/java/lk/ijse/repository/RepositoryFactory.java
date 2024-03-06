package lk.ijse.repository;

import lk.ijse.repository.impl.BookRepositoryImpl;
import lk.ijse.repository.impl.CustomerRepositoryImpl;
import lk.ijse.repository.impl.LoginRepositoryImpl;

public class RepositoryFactory {
    static RepositoryFactory repositoryFactory;
    private RepositoryFactory(){}

    public static RepositoryFactory getRepositoryFactory(){
        return repositoryFactory == null ? new RepositoryFactory() : repositoryFactory;
    }

    public enum RepositoryTypes{
        LOGIN, CUSTOMER, BOOK
    }

    public SuperRepository getRepository(RepositoryTypes repositoryTypes){
        switch (repositoryTypes){
            case LOGIN: return new LoginRepositoryImpl();
            case CUSTOMER: return new CustomerRepositoryImpl();
            case BOOK: return new BookRepositoryImpl();
            default:return null;
        }
    }
}
