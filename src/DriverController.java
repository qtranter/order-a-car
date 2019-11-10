import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class DriverController {


    @FXML
    private Label nameLabel;

    @FXML
    private Label phoneLabel;

    @FXML
    private Label creditCardLabel;

    @FXML
    private Label profileRatings;

    @FXML
    private Label totalOAC;

    @FXML
    private Button btnEditName;

    @FXML
    private Button btnEditPhone;

    @FXML
    private Button btnEditCreditCard;

    @FXML
    private Button btnLogOut;

    @FXML
    private Label tripPaymentLabel;

    @FXML
    private Label totalTipLabel;

    @FXML
    private Label totalEarnedLabel;

    @FXML
    private Button btnSelectTripOne;

    @FXML
    private Button btnSelectTripTwo;

    @FXML
    private Button btnSelectTripThree;

    @FXML
    private Label selectTripNameOne;

    @FXML
    private Label selectTripNameTwo;

    @FXML
    private Label selectTripThree;

    @FXML
    private Label selectTripDestinationOne;

    @FXML
    private Label selectTripDestinationTwo;

    @FXML
    private Label selectTripDestinationThree;

    @FXML
    private Label selectTripAddressOne;

    @FXML
    private Label selectTripAddressTwo;

    @FXML
    private Label selectTripAddressThree;

    @FXML
    private Label historyNameOne;

    @FXML
    private Label historyAddressOne;

    @FXML
    private Label historyPaymentOne;

    @FXML
    private Label historyTipOne;

    @FXML
    private Label historyNameTwo;

    @FXML
    private Label historyAddressTwo;

    @FXML
    private Label historyPaymentTwo;

    @FXML
    private Label historyTipTwo;

    @FXML
    private Label historyNameThree;

    @FXML
    private Label historyAddressThree;

    @FXML
    private Label historyPaymentThree;

    @FXML
    private Label historyTipThree;

    private int userID;
    private DatabaseManager databaseManager = new DatabaseManager();

    public DriverController(int userID) throws SQLException {
        this.userID = userID;
    }

    @FXML
    void changeName(ActionEvent event) {
    }

    @FXML
    void handleLogOut(ActionEvent event) throws IOException {

            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene logInScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(logInScene);
            window.show();
        }

    

    private void setScene(ActionEvent event, Scene nextScene) {
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(nextScene);
        window.show();
    }

}



