package lk.ijse.service;

import lk.ijse.projection.BookIdsAndTitles;

import java.util.List;

public interface PlaceOrderService extends SuperService{
    public List<BookIdsAndTitles> getIdsAndTitles();
}
