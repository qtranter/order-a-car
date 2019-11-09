package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.h2.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class RegistrationController implements Initializable {
    private DatabaseManager databaseManager;

    public RegistrationController() throws SQLException {
        databaseManager = new DatabaseManager();
    }

    @FXML
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtUserName;

    @FXML
    private PasswordField passMain;

    @FXML
    private PasswordField passConfirm;

    @FXML
    private RadioButton btnDriver;

    @FXML
    private RadioButton btnRider;

    @FXML
    private Label txtPasswordNotMatch;

    @FXML
    private Button btnSubmit;

    @FXML
    private AnchorPane areaDriver;

    @FXML
    private ComboBox<String> chcStateCar;

    @FXML
    private TextField txtDriverNum;

    @FXML
    private TextField txtLicensePlate;

    @FXML
    private TextField txtYear;

    @FXML
    private TextField txtModel;

    @FXML
    private TextField txtMake;

    @FXML
    private Label txtOnlyOneUserType;

    @FXML
    private Label txtMissingItems;

    @FXML
    private ComboBox<Integer> chcYear;

    @FXML
    private Label txtDriverNumValid;

    @FXML
    private Label txtLicensePlateValid;

    @FXML
    void checkDriverNum(KeyEvent event) {
        if (txtDriverNum.getText().length() > 13 || txtDriverNum.getText().length() <= 11) {
            txtDriverNumValid.setVisible(true);
        } else {
            txtDriverNumValid.setVisible(false);
        }

    }

    @FXML
    void checkLicensePlate(KeyEvent event) {
        if (txtLicensePlate.getText().length() > 9) {
            txtLicensePlateValid.setVisible(true);
        } else {
            txtLicensePlateValid.setVisible(false);
        }
    }


    @FXML
    void checkPasswordMatch(KeyEvent event) {
        if (passMain.getText().equals(passConfirm.getText())) {
            txtPasswordNotMatch.setVisible(false);
        } else {
            txtPasswordNotMatch.setVisible(true);
        }
    }

    @FXML
    void enableCarInfoSection(ActionEvent event) {
        if (btnDriver.isSelected()) {
            areaDriver.setDisable(false);
        } else {
            areaDriver.setDisable(true);
        }
        if (btnRider.isSelected() && btnDriver.isSelected()) {
            txtOnlyOneUserType.setVisible(true);
        } else {
            txtOnlyOneUserType.setVisible(false);
        }
    }

    @FXML
    void submitForm(ActionEvent event) throws IOException {
        boolean finishRegistration = true;
        TextField txtArray[] = {txtFirstName, txtLastName, txtUserName, passConfirm};
        TextField txtArrayDriver[] = {txtMake, txtModel, txtLicensePlate, txtDriverNum};
        List<TextField> txtList = new ArrayList<>(Arrays.asList(txtArray));
        if (btnDriver.isSelected()) {
            List<TextField> txtDriverList = new ArrayList<>();
            txtDriverList = Arrays.asList(txtArrayDriver);
            txtList.addAll(txtDriverList);
        }
        String output = null;
        for (TextField text : txtList) {
            if (text.getText() == null || text.getText().trim().isEmpty()) {
                if (output == null) {
                    output = "Missing: " + text.getPromptText();
                    finishRegistration = false;
                } else {
                    output += ", " + text.getPromptText();
                }
            }
        }
        if (!btnDriver.isSelected() && !btnRider.isSelected()) {
            if (output == null) {
                output = "Missing: User Type";
                finishRegistration = false;
            } else {
                output += ", User Type";
            }
        }
        if (txtPasswordNotMatch.isVisible() || txtOnlyOneUserType.isVisible() || txtLicensePlateValid.isVisible()
                || txtDriverNumValid.isVisible()) {
            finishRegistration = false;
        }
        if (chcYear.getSelectionModel().getSelectedItem() == null && btnDriver.isSelected()) {
            if (output == null) {
                output = "Missing: Year";
                finishRegistration = false;
            } else {
                output += ", Year";
            }
        }
        if (chcStateCar.getSelectionModel().getSelectedItem() == null && btnDriver.isSelected()) {
            if (output == null) {
                output = "Missing: State";
                finishRegistration = false;
            } else {
                output += ", State";
            }
        }
        if (finishRegistration) {
            txtMissingItems.setVisible(false);
            completeRegistration();
//            final Node source = (Node) event.getSource();
//            final Stage stage = (Stage) source.getScene().getWindow();
//            stage.close(); //closes the window
            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Scene riderScene = new Scene(root);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(riderScene);
            window.show();
        } else {
            txtMissingItems.setText(output);
            txtMissingItems.setVisible(true);
        }
    }

    public void completeRegistration() {
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String userName = txtUserName.getText();
        String password = passMain.getText();
        String make = txtMake.getText();
        String model = txtModel.getText();
        String licensePlate = txtLicensePlate.getText();
        String driverNum = txtDriverNum.getText();
        try {
            int year = chcYear.getSelectionModel().getSelectedItem();
            String state = chcStateCar.getSelectionModel().getSelectedItem();
        } catch (Exception e) {

        }
        int OakCoins = 100;
        int userType = 0;
        if (btnRider.isSelected()) {
            userType = 0;
            //insert a way to insert data for database here for Riders
        } else if (btnDriver.isSelected()) {
            userType = 1;
            //insert a way to insert data for database here for drivers
        }
        databaseManager.insert(1, firstName, lastName, userType, userName, password);

    }

    public void initialize(URL url, ResourceBundle resource) {
        List<Integer> monthList = new ArrayList<>();
        for (int x = 1; x <= 12; x++) {
            monthList.add(x);
        }
        ObservableList month = FXCollections.observableArrayList(monthList);
        List<Integer> yearList = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int y = year + 1; y > 1920; y--) {
            yearList.add(y);
        }
        ObservableList yearObsList = FXCollections.observableArrayList(yearList);
        chcYear.setItems(yearObsList);
        String states[] = {"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN",
                "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
                "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI",
                "WY"};
        List<String> statesList = new ArrayList<>();
        statesList.addAll(Arrays.asList(states).subList(0, 59));
        ObservableList statesObsList = FXCollections.observableArrayList(statesList);
        chcStateCar.getItems().addAll(statesObsList);
    }


}
