package lk.ijse.service;

import lk.ijse.dto.BorrowBookDto;
import lk.ijse.dto.PlaceOrderDto;
import lk.ijse.projection.BookIdsAndTitles;

import java.util.List;

public interface PlaceOrderService extends SuperService{
    public List<BookIdsAndTitles> getIdsAndTitles();
    public boolean placeOrder(PlaceOrderDto placeOrderDto);
}
