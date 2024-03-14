package lk.ijse.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dto.CustomerDto;
import lk.ijse.service.CustomerService;
import lk.ijse.service.ServiceFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class ForgetPasswordFormController {
    public TextField txtUsername;
    public TextField txtPin;
    public AnchorPane forgetPasswordContext;

    private int randomValue;
    CustomerService customerService = (CustomerService) ServiceFactory.getServiceFactory().getService(ServiceFactory.ServiceTypes.CUSTOMER);

    public void btnOkOnAction(ActionEvent actionEvent) throws MessagingException {
        CustomerDto customerDto = customerService.getCustomerUsingUsername(txtUsername.getText());

        Random random = new Random();
        randomValue = random.nextInt(9000) + 1000;

        // Set properties
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", 587);

        // Create a Session object
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("bookworm999912@gmail.com", "szxl beis rdwe seqc");
            }
        });

        try {
            // Create a MimeMessage object
            Message message = new MimeMessage(session);
            // Set From: header field
            message.setFrom(new InternetAddress("bookworm999912@gmail.com"));
            // Set To: header field
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerDto.getEMail()));
            // Set Subject: header field
            message.setSubject("Account Reset");
            // Set Content: text/plain
            message.setText("Your PIN : "+randomValue);

            // Send message
            Transport.send(message);
            //System.out.println("Email sent successfully.");

            new Alert(Alert.AlertType.INFORMATION, "We just send email to you. Check it and enter PIN!").show();

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if (txtPin.getText().equals(String.valueOf(randomValue))){
            Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/loginForm.fxml"));
            Scene scene = new Scene(rootNode);
            Stage stage = new Stage();
            stage.centerOnScreen();
            stage.setScene(scene);
            stage.show();
            LoginPageController.closeStage();
        }else {
            new Alert(Alert.AlertType.ERROR, "PIN Incorrect!").show();
        }
    }
}
