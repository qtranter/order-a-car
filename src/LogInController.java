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

    public void handleLoginButton(javafx.event.ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String userName = loginUsername.getText();
        String password = loginPassword.getText();

        if (databaseManager.userExists(userName, password)) {
            if (isDriver(userName, password)) {
                loginDriver(actionEvent, userName, password);
            } else if (isRider(userName, password)) {
                loginRider(actionEvent, userName, password);
            }
        } else {
            System.out.println("Login failed!");
        }
    }

    private void loginDriver(ActionEvent actionEvent, String userName, String password) throws SQLException, IOException {
        DriverController driverController = new DriverController(databaseManager.getUserID(userName, password));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Driver.fxml"));
        loader.setController(driverController);
        Scene userScene = new Scene(loader.load());
        setScene(actionEvent, userScene);
    }

<<<<<<< HEAD
    private void loginRider(ActionEvent actionEvent, String userName, String password) throws SQLException, IOException {
        RiderProfileController riderController = new RiderProfileController(databaseManager.getUserID(userName, password));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("riderProfile.fxml"));
=======
    private void loginRider(ActionEvent actionEvent, String userName, String password) throws SQLException, IOException, ClassNotFoundException {
        RiderProfileController riderController = new RiderProfileController(databaseManager.getUserID(userName, password));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RiderProfile.fxml"));
>>>>>>> blake's-rider-branch
        loader.setController(riderController);
        Scene userScene = new Scene(loader.load());
        setScene(actionEvent, userScene);
    }

    private boolean isDriver(String userName, String password) throws SQLException {
        return databaseManager.userType(userName, password) == DRIVER_TYPE;
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
        Scene signInScene = new Scene(root, 600, 386);

        setScene(actionEvent, signInScene);
    }
}
