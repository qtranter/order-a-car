package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController {
    @FXML
    private TextField loginUsername;

    @FXML
    private TextField loginPassword;

    private DatabaseManager databaseManager;

    public LogInController() throws SQLException {
        databaseManager = new DatabaseManager();
    }

    public void handleLoginButton(javafx.event.ActionEvent actionEvent) throws IOException, SQLException {
        String userName = loginUsername.getText();
        String password = loginPassword.getText();

        if (databaseManager.validateLogin(userName, password)) {
            if(databaseManager.userType(userName, password) == 1) {
                System.out.println("Login Successful!");
                Parent root = FXMLLoader.load(getClass().getResource("Driver.fxml"));
                Scene riderScene = new Scene(root);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                window.setScene(riderScene);
                window.show();
            }
            else if(databaseManager.userType(userName, password) == 0) {
                System.out.println("Login Successful!");
                Parent root = FXMLLoader.load(getClass().getResource("Rider.fxml"));
                Scene riderScene = new Scene(root);

                Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

                window.setScene(riderScene);
                window.show();
            }

        } else {
            System.out.println("Login failed!");
        }

        System.out.println(userName);
        System.out.println(password);
    }

    public void handleSignInButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene signInScene = new Scene(root, 554, 600);

        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(signInScene);
        window.show();
    }
}
