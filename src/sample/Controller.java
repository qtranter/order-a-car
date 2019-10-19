package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private TextField loginUsername;

    @FXML
    private TextField loginPassword;

    DatabaseManager databaseManager;

    public Controller() throws SQLException {
        databaseManager = new DatabaseManager();
    }

    public void handleLoginButton(javafx.event.ActionEvent actionEvent) {
        String userName = loginUsername.getText();
        String password = loginPassword.getText();

        if(databaseManager.validate(userName, password)) {
            System.out.println("Login Successful!");
        }
        else {
            System.out.println("Login failed!");
        }

        System.out.println(userName);
        System.out.println(password);
    }
}
