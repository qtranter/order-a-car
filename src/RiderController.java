import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private TextField pickup;
    @FXML
    private TextField destination;
    @FXML
    public TableView<Ride> tableview;
    @FXML
    public TableColumn<Ride, String> pickupColumn;
    @FXML
    public TableColumn<Ride, String> destinationColumn;
    @FXML
    public TableColumn<Ride, Double> costColumn;
    @FXML
    public TableColumn<Ride, String> dateColumn;

    private ObservableList<Ride> data = FXCollections.observableArrayList();;

    private DatabaseManager databaseManager;
    private int userID;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            firstName.setText(databaseManager.getFirstName(userID));
            lastName.setText(databaseManager.getLastName(userID));
            userName.setText(databaseManager.getUserName(userID));
            coins.setText(databaseManager.getCoins(userID));

            data.addAll(databaseManager.getRides(userID));
            pickupColumn.setCellValueFactory(new PropertyValueFactory<>("pickupAddress"));
            destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
            costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            tableview.setItems(data);
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

    public void orderCar(ActionEvent actionEvent) throws SQLException {
        String pickupLocation = pickup.getText();
        String dest = destination.getText();
        databaseManager.insertRide(userID, pickupLocation, dest, databaseManager.getFirstName(userID));
    }
}
