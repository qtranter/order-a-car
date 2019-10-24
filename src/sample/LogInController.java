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
    private static final int RIDER_TYPE = 0;
    private static final int DRIVER_TYPE = 1;

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

        if (databaseManager.userExists(userName, password)) {
            if (isDriver(userName, password)) {
                loginUser(actionEvent, "Driver.fxml");
            } else if (isRider(userName, password)) {
                loginUser(actionEvent, "Rider.fxml");
            }
        } else {
            System.out.println("Login failed!");
        }
        System.out.println(userName);
        System.out.println(password);
    }

    private boolean isDriver(String userName, String password) throws SQLException {
        return databaseManager.userType(userName, password) == DRIVER_TYPE;
    }

    private void loginUser(ActionEvent actionEvent, String scenePath) throws IOException {
        System.out.println("Login Successful!");
        Parent root = FXMLLoader.load(getClass().getResource(scenePath));
        Scene driverScene = new Scene(root);

        setScene(actionEvent, driverScene);
    }

    private void setScene(ActionEvent actionEvent, Scene nextScene) {
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        window.setScene(nextScene);
        window.show();
    }

    private boolean isRider(String userName, String password) throws SQLException {
        return databaseManager.userType(userName, password) == RIDER_TYPE;
    }

    public void handleSignInButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Registration.fxml"));
        Scene signInScene = new Scene(root, 554, 600);

        setScene(actionEvent, signInScene);
    }
}
