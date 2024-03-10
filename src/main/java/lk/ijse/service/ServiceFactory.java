package lk.ijse.service;

import lk.ijse.service.impl.*;

public class ServiceFactory {
    static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return serviceFactory == null ? new ServiceFactory() : serviceFactory;
    }
    public enum ServiceTypes{
        LOGIN, CUSTOMER, BOOK, ORDER, ORDER_DETAIL, PLACE_ORDER
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case LOGIN: return new LoginServiceImpl();
            case CUSTOMER: return new CustomerServiceImpl();
            case BOOK: return new BookServiceImpl();
            case ORDER: return new OrderServiceImpl();
            case ORDER_DETAIL: return new OrderDetailServiceImpl();
            case PLACE_ORDER: return new PlaceOrderServiceImpl();
            default: return null;
        }
    }
}
