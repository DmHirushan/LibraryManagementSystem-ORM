package lk.ijse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dto.BookDto;
import lk.ijse.entity.Book;
import lk.ijse.entity.Image;
import lk.ijse.repository.BookRepository;
import lk.ijse.repository.CustomerRepository;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.impl.BookRepositoryImpl;
import lk.ijse.service.BookService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.SessionFactoryConfig;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
        Scene scene = new Scene(rootNode);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();

    }
}
