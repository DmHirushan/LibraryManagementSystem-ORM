package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import lk.ijse.repository.BookRepository;
import lk.ijse.service.BookService;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

import java.awt.*;

public class UserDashbordContentFormController {
    public Label lblLibraryName;
    public Label lblMemberCount;
    public Label lblBookCount;

    BookService bookService = (BookService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.BOOK);
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);

    public void initialize(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(lblLibraryName.textFillProperty(), javafx.scene.paint.Color.WHITE)),
                new KeyFrame(Duration.seconds(1), new KeyValue(lblLibraryName.textFillProperty(), Color.GRAY)),
                new KeyFrame(Duration.seconds(2), new KeyValue(lblLibraryName.textFillProperty(), Color.BLUE))
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

        loadBookCount();
        loadMemberCount();

    }

    private void loadMemberCount() {
        lblMemberCount.setText(String.valueOf(customerService.getCustomerCount()));
    }

    private void loadBookCount() {
        lblBookCount.setText(String.valueOf(bookService.getBookCount()));
    }
}
