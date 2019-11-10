import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RiderController implements Initializable {

    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label userName;
    @FXML
    private Label coins;

    private DatabaseManager databaseManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            firstName.setText(databaseManager.getFirstName(LogInController.userID));
            lastName.setText(databaseManager.getLastName(LogInController.userID));
            userName.setText(databaseManager.getUserName(LogInController.userID));
            coins.setText(databaseManager.getCoins(LogInController.userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RiderController() throws SQLException {
        databaseManager = new DatabaseManager();
    }
}
