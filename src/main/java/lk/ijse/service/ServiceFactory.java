package lk.ijse.service;

import lk.ijse.service.impl.BookServiceImpl;
import lk.ijse.service.impl.CustomerServiceImpl;
import lk.ijse.service.impl.LoginServiceImpl;

public class ServiceFactory {
    static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return serviceFactory == null ? new ServiceFactory() : serviceFactory;
    }
    public enum ServiceTypes{
        LOGIN, CUSTOMER, BOOK
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case LOGIN: return new LoginServiceImpl();
            case CUSTOMER: return new CustomerServiceImpl();
            case BOOK: return new BookServiceImpl();
            default: return null;
        }
    }
}
