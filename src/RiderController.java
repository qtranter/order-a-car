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

    private int userID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            firstName.setText(databaseManager.getFirstName(userID));
            lastName.setText(databaseManager.getLastName(userID));
            userName.setText(databaseManager.getUserName(userID));
            coins.setText(databaseManager.getCoins(userID));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RiderController() throws SQLException {
        databaseManager = new DatabaseManager();
    }

    public RiderController(int userID) throws SQLException {
        this.userID = userID;
        this.databaseManager = new DatabaseManager();
    }

}
