package lk.ijse.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dto.AdminDto;
import lk.ijse.dto.CustomerDto;
import lk.ijse.entity.Admin;
import lk.ijse.entity.Book;
import lk.ijse.service.AdminService;
import lk.ijse.service.CustomerService;
import lk.ijse.service.LoginService;
import lk.ijse.service.ServiceFactory;
import lk.ijse.util.DateAndTime;

import java.io.IOException;

public class LoginPageController {
    public ImageView loginFormContext;
    public AnchorPane loginContext;
    public Button btnAdmin;
    public Button btnUser;
    public Label lblDateAndTime;
    public Hyperlink hyperlinkDontHaveAccount;
    public TextField txtUsername;
    public TextField txtPassword;
    public AnchorPane loginPageContext;
    public static String username;

    private static Stage stage;
    AdminService adminService = (AdminService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.ADMIN);
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);

    public void initialize() throws IOException {
        new Book();
        DateAndTime.manageDateAndTime(lblDateAndTime);
        //saveAdmin();
    }

    public void hyperlinkSignInOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/signInForm.fxml"));
        Parent root = loader.load();

        // Set the scene
        Scene scene = new Scene(root, 1080, 580);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Sign In Form");
        stage.show();
    }

    /*public void saveAdmin(){
        Admin admin = new Admin(txtUsername.getText(), txtPassword.getText());
        Long id = loginService.save(admin);
        System.out.println("id : " + id);
    }*/

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        username = txtUsername.getText();

        if (txtUsername.equals(null) || username.startsWith("AD-")){
            AdminDto adminDto = adminService.getUsingUsername(username);
            if (adminDto != null){
                if (txtPassword.getText().equals(adminDto.getPassword())){
                    stage = (Stage) loginPageContext.getScene().getWindow();
                    stage.setScene(
                            new Scene(FXMLLoader.load(getClass().getResource("/view/adminDashboardForm.fxml")))
                    );
                }else {
                    new Alert(Alert.AlertType.WARNING, "Password Incorrect!").show();
                }
            }else {
                new Alert(Alert.AlertType.WARNING, "Please Enter Valid Useranme!").show();
            }

        }else if (checkExists(txtUsername.getText())){
            if (getUser(txtUsername.getText(), txtPassword.getText())) {
                loading();
                stage = (Stage) loginPageContext.getScene().getWindow();
                stage.setScene(
                        new Scene(FXMLLoader.load(getClass().getResource("/view/userDashboardForm.fxml")))
                );
            }else {
                new Alert(Alert.AlertType.INFORMATION, "Password Incorrect!").show();
            }
        }else {
            new Alert(Alert.AlertType.WARNING, "Please Enter Valid Username!").show();
        }
    }

    private void loading() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loggedInForm.fxml"));
        Parent root = loader.load();

        // Set the scene
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("FXML Example");
        stage.show();

        // Close the stage after one second
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1.8), event -> stage.close()));
        timeline.play();
    }

    public boolean checkExists(String username){
        boolean x = customerService.isExists(username);
        //System.out.println(x);
        return x;
    }
    public boolean getUser(String username, String password){
        CustomerDto customerDto = customerService.getCustomerUsingUsername(username);
        return customerDto.getPassword().equals(password);
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) throws IOException {
        btnLoginOnAction(actionEvent);
    }

    public static void  closeStage(){
        stage.close();
    }
}
