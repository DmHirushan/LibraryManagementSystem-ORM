package lk.ijse.service;

import lk.ijse.service.impl.BookServiceImpl;
import lk.ijse.service.impl.CustomerServiceImpl;
import lk.ijse.service.impl.LoginServiceImpl;
import lk.ijse.service.impl.PlaceOrderServiceImpl;

public class ServiceFactory {
    static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return serviceFactory == null ? new ServiceFactory() : serviceFactory;
    }
    public enum ServiceTypes{
        LOGIN, CUSTOMER, BOOK, PLACE_ORDER
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case LOGIN: return new LoginServiceImpl();
            case CUSTOMER: return new CustomerServiceImpl();
            case BOOK: return new BookServiceImpl();
            case PLACE_ORDER: return new PlaceOrderServiceImpl();
            default: return null;
        }
    }
}
