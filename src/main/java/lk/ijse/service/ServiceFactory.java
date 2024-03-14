package lk.ijse.service;

import lk.ijse.service.impl.*;

public class ServiceFactory {
    static ServiceFactory serviceFactory;
    private ServiceFactory(){}

    public static ServiceFactory getServiceFactory(){
        return serviceFactory == null ? new ServiceFactory() : serviceFactory;
    }
    public enum ServiceTypes{
        ADMIN, LOGIN, CUSTOMER, BOOK, ORDER, ORDER_DETAIL, PLACE_ORDER, BRANCH
    }

    public SuperService getService(ServiceTypes serviceTypes){
        switch (serviceTypes){
            case ADMIN: return new AdminServiceImpl();
            case LOGIN: return new LoginServiceImpl();
            case CUSTOMER: return new CustomerServiceImpl();
            case BOOK: return new BookServiceImpl();
            case ORDER: return new OrderServiceImpl();
            case ORDER_DETAIL: return new OrderDetailServiceImpl();
            case PLACE_ORDER: return new PlaceOrderServiceImpl();
            case BRANCH: return new BranchServiceImpl();
            default: return null;
        }
    }
}
