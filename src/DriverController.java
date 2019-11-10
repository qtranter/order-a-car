import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DriverController implements Initializable {



    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label userName;
    @FXML
    private Label coins;
    @FXML
    private Label rating;

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
    public TableView<Ride> tableview;
    @FXML
    public TableColumn<Ride, String> name;
    @FXML
    public TableColumn<Ride, String> pickup;
    @FXML
    public TableColumn<Ride, String> destination;
    private ObservableList<Ride> data = FXCollections.observableArrayList();;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            //System.out.println(databaseManager.getAvailableRides());
            firstName.setText(databaseManager.getFirstName(userID));
            lastName.setText(databaseManager.getLastName(userID));
            userName.setText(databaseManager.getUserName(userID));
            coins.setText(databaseManager.getCoins(userID));
            rating.setText(databaseManager.getRating(userID));

            data.addAll(databaseManager.getAvailableRides());
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            pickup.setCellValueFactory(new PropertyValueFactory<>("pickupAddress"));
            destination.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
            tableview.setItems(data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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



