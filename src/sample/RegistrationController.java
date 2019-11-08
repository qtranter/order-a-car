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
    private TextField txtName;

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
    private ComboBox<?> chcStateCar;

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
    private TextField txtFirstName;

    @FXML
    private TextField txtLastName;

    @FXML
    private TextField txtCreditCardNum;

    @FXML
    private ComboBox<?> chcMonth;

    @FXML
    private ComboBox<?> chcYear;

    @FXML
    private TextField txtCVV;

    @FXML
    private ComboBox<?> chcStateBilling;

    @FXML
    private TextField txtAddress1;

    @FXML
    private TextField txtCity;

    @FXML
    private TextField txtAddress2;

    @FXML
    private TextField txtZip;

    @FXML
    private Label txtMissingItems;

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
    }

    @FXML
    void submitForm(ActionEvent event) throws IOException {
        boolean nameEmpty = txtName.getText() == null || txtName.getText().trim().isEmpty();
        boolean userNameEmpty = txtUserName.getText() == null || txtUserName.getText().trim().isEmpty();
        boolean passwordEmpty = txtPasswordNotMatch.isVisible() || passMain.getText() == null ||
                passMain.getText().trim().isEmpty() || passConfirm.getText() == null || passConfirm.getText().trim().isEmpty();
        boolean userTypeNotSelected = (!btnDriver.isSelected() && !btnRider.isSelected());
//        StringUtils.isNullOrEmpty(txtUserName.getText());
        boolean firstNameEmpty = txtFirstName.getText() == null || txtFirstName.getText().trim().isEmpty();
        boolean lastNameEmpty = txtLastName.getText() == null || txtLastName.getText().trim().isEmpty();
        boolean creditCardNumEmpty = txtCreditCardNum.getText() == null || txtCreditCardNum.getText().trim().isEmpty();
        boolean monthEmpty = chcMonth.getSelectionModel().isEmpty();
//        System.out.println(monthEmpty);
//        System.out.println("name " + nameEmpty);
//        System.out.println("type " + userTypeNotSelected);
        boolean yearCardEmpty = chcYear.getSelectionModel().isEmpty();
        boolean cvvEmpty = txtCVV.getText() == null || txtCVV.getText().trim().isEmpty();
        boolean address1Empty = txtAddress1.getText() == null || txtAddress1.getText().trim().isEmpty();
        boolean cityEmpty = txtCity.getText() == null || txtCity.getText().trim().isEmpty();
        boolean stateBillEmpty = chcStateBilling.getSelectionModel().isEmpty();
        boolean zipEmpty = txtZip.getText() == null || txtZip.getText().trim().isEmpty();
        if (!(nameEmpty || userNameEmpty || passwordEmpty || userTypeNotSelected || firstNameEmpty || lastNameEmpty || creditCardNumEmpty || monthEmpty || yearCardEmpty || cvvEmpty || address1Empty || cityEmpty || stateBillEmpty || zipEmpty)) {
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
            txtMissingItems.setVisible(true);
        }

    }

    public void completeRegistration() {
        String name = txtName.getText();
        String userName = txtUserName.getText();
        String password = passMain.getText();
        int userType;
        if (btnRider.isSelected()) {
            userType = 0;
        } else if (btnDriver.isSelected()) {
            userType = 1;
        } else if (btnDriver.isSelected() && btnRider.isSelected()) {
            userType = 1;
        } else {
            userType = 2;
        }
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
//        String creditCardNumber = txtCreditCardNum.getText(); //String for now
//        int monthCard = (Integer) chcMonth.getValue();
//        int yearCard = (Integer) chcYear.getValue();
//        String cvv = txtCVV.getText(); //String for now
        String address1 = txtAddress1.getText();
        String address2 = txtAddress2.getText();
        String city = txtCity.getText();
        String state = (String) chcStateBilling.getValue();
        String zip = txtZip.getText(); //String for now

        int rating = 0;
        int coins = 100;
        String address = address1 + ", " + address2 + city + " " + state + ", " + zip;
        databaseManager.insert(firstName, lastName, userName, password, rating, coins, address, userType);
    }

    public void initialize(URL url, ResourceBundle resource) {
        List<Integer> monthList = new ArrayList<>();
        for (int x = 1; x <= 12; x++) {
            monthList.add(x);
        }
        ObservableList month = FXCollections.observableArrayList(monthList);
        chcMonth.getItems().addAll(month);
        List<Integer> yearList = new ArrayList<>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        for (int y = year; y < (year + 20); y++) {
            yearList.add(y);
        }
        ObservableList yearObsList = FXCollections.observableArrayList(yearList);
        chcYear.getItems().addAll(yearObsList);
        String states[] = {"AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN",
                "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
                "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI",
                "WY"};
        List<String> statesList = new ArrayList<>();
        statesList.addAll(Arrays.asList(states).subList(0, 59));
//        System.out.println(states.length);
        ObservableList statesObsList = FXCollections.observableArrayList(statesList);
        chcStateBilling.getItems().addAll(statesObsList);
        chcStateCar.getItems().addAll(statesObsList);
    }


}
