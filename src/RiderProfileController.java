package rider;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class RiderProfileController implements Initializable {
    @FXML private Button OrderACarButton, EditProfileButton, EditPrefButton, AddCoinsButton, changeProfilePic;
    @FXML private Button OACPlaceOrderButton, QuickCarButton, OrderLater, logout;
    @FXML private Label dateDisplay;
    @FXML private Label timeDisplay;
    @FXML private Button prefSavePrefButton;
    @FXML private Button PrefPickUpHistButton;
    @FXML private Button PrefDestHistButton;
    @FXML private ChoiceBox<String> PrefVehicleTypeDropBox;
    @FXML private ChoiceBox<Integer> PrefVehicleCapacityDropBox;
    @FXML private ChoiceBox<Integer> PrefDriverRatingDropBox;
    @FXML private Button submit;
    @FXML private Button submitPickupHistory;
    @FXML private Button submitDestHistory;

    @FXML private Label RiderCoinsLabel, RiderRatingLabel;
    @FXML private Label userName;
    @FXML private Label lastName;
    @FXML private Label firstName;
    @FXML private TextField AddCoinsTextField;
    @FXML private ImageView RiderProfileImage;

    @FXML private ChoiceBox<String> OACVehicleTypeDropBox, OACPickStateDropBox, OACDestStateDropBox;
    @FXML private ChoiceBox<Integer> NumOfRidersDropBox, OACDriverRatingDropBox;

    @FXML private TextField StreetAddressTextField, CityTextField, ZipcodeTextField;
    @FXML private TextField destStreetAddressTextField, destCityTextField, destZipcodeTextField;

    @FXML private TextField PrefStreetAddressTextField, PrefCityTextField, PrefZipcodeTextField;
    @FXML private ChoiceBox<String> PrefStateDropBox;

    @FXML private TextField PrefDestZipcodeTextField;
    @FXML private TextField PrefDestStreetAddressTextField;
    @FXML private TextField PrefDestCityTextField;
    @FXML private ChoiceBox<String> PrefDestStateDropBox;

    @FXML private ChoiceBox<String> AMorPM;
    @FXML private TextField dateDay;
    @FXML private TextField dateMonth;
    @FXML private TextField dateYear;
    @FXML private TextField timeHour;
    @FXML private TextField timeMinute;

    private FileChooser chosen = new FileChooser();
    private String imagepath;

    private ObservableList<String> states = FXCollections.observableArrayList("AL", "AK", "AS", "AZ", "AR", "CA", "CO", "CT", "DE", "DC", "FM", "FL", "GA", "GU", "HI", "ID", "IL", "IN",
            "IA", "KS", "KY", "LA", "ME", "MH", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC",
            "ND", "MP", "OH", "OK", "OR", "PW", "PA", "PR", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VI", "VA", "WA", "WV", "WI",
            "WY");
    private ObservableList<Integer> numOfRiders = FXCollections.observableArrayList(1,2,3,4,5,6,7,8);
    private ObservableList<Integer> driverRating = FXCollections.observableArrayList(5,4,3,2,1,0);
    private ObservableList<String> vehicType = FXCollections.observableArrayList("Any","Car","Truck","Sedan","SUV","Van");

    private ObservableList<String> amORpm = FXCollections.observableArrayList("AM","PM");

    @FXML public TableView<RideHistory> historyTable;
    @FXML public TableColumn<RideHistory, String> pickupColumn;
    @FXML public TableColumn<RideHistory, String> destinationColumn;
    @FXML public TableColumn<RideHistory, Double> costColumn;
    @FXML public TableColumn<RideHistory, String> dateColumn;
    @FXML public TableColumn<RideHistory, String> nameColumn;
    private ObservableList<RideHistory> data = FXCollections.observableArrayList();;

    public String imageSelection() throws Exception{
        chosen.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
        File file = chosen.showOpenDialog(new Stage());
        if(file != null){
            imagepath = file.toURI().toURL().toString();
            Image image = new Image(imagepath);
            RiderProfileImage.setImage(image);


        }
        return imagepath;
    }

    private int userID;
    private DatabaseManager databaseManager; = new DatabaseManager();
    private DatabaseManager databaseManager;

    public RiderProfileController(int userID) throws SQLException {
        this.userID = userID;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        AMorPM.setItems(amORpm);
        AddCoinsTextField.setText("");
        dateDay.setText("");
        dateMonth.setText("");

        if(imagepath != null) {
            Image image = new Image(imagepath);
            RiderProfileImage.setImage(image);
        }

//        firstName.setText("Bob");
//        lastName.setText("Smith");
//        userName.setText("BobSmith");
//        RiderCoinsLabel.setText("0.0");
//        RiderRatingLabel.setText("5.0");

        try {
        firstName.setText(databaseManager.getFirstName(userID));
        lastName.setText(databaseManager.getLastName(userID));
        userName.setText(databaseManager.getUserName(userID));
        RiderCoinsLabel.setText(databaseManager.getCoins(userID));
        RiderRatingLabel.setText(databaseManager.getRating(userID));

        if(imagepath != null) {
            Image databaseManager.getImage(userID) = new Image(imagepath);
            RiderProfileImage.setImage(databaseManager.getImage(userID));

            data.addAll(databaseManager.getRides(userID));
            pickupColumn.setCellValueFactory(new PropertyValueFactory<>("pickupAddress"));
            destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
            costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableview.setItems(data);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        OACVehicleTypeDropBox.setItems(FXCollections.observableArrayList(vehicType));
        OACVehicleTypeDropBox.setValue("Any");

        NumOfRidersDropBox.setItems(FXCollections.observableArrayList(numOfRiders));
        NumOfRidersDropBox.setValue(1);

        OACDriverRatingDropBox.setItems(FXCollections.observableArrayList(driverRating));
        OACDriverRatingDropBox.setValue(5);

        OACPickStateDropBox.setItems(FXCollections.observableArrayList(states));
        OACPickStateDropBox.setValue("FL");

        OACDestStateDropBox.setItems(FXCollections.observableArrayList(states));
        OACDestStateDropBox.setValue("FL");

        PrefStateDropBox.setItems(FXCollections.observableArrayList(states));
        PrefStateDropBox.setValue("FL");

        PrefDestStateDropBox.setItems(FXCollections.observableArrayList(states));
        PrefDestStateDropBox.setValue("FL");

//        PrefStreetAddressTextField.setText("a");
//        PrefCityTextField.setText("b");
//        PrefZipcodeTextField.setText("c");
//
//        PrefDestZipcodeTextField.setText("aa");
//        PrefDestStreetAddressTextField.setText("bb");
//        PrefDestCityTextField.setText("cc");

        PrefStateDropBox.setValue(databaseManager.getPrefState(userID));
        PrefDestStateDropBox.setValue(databaseManager.getPrefDest(userID));
        PrefVehicleTypeDropBox.setValue(databaseManager.getPrefVehicleType(userID));
        PrefVehicleCapacityDropBox.setValue(databaseManager.getPrefVehicleCapacity(userID));
        PrefDriverRatingDropBox.setValue(databaseManager.getPrefDriverRating(userID));

    }

    public void RiderProfileController() throws SQLException{
        databaseManager = new DatabaseManager();
    }

    public void RiderController(int userID) throws SQLException {
        this.userID = userID;
        this.databaseManager = new DatabaseManager();
    }

    public void coinCalculation(){
        String stringOldCoins = RiderCoinsLabel.getText();
        double doubOldCoins = Double.parseDouble(stringOldCoins);

        String stringCoins = AddCoinsTextField.getText();
        double doubAddedCoins = Double.parseDouble(stringCoins);

        Double doubNewCoins = doubOldCoins + doubAddedCoins;
        RiderCoinsLabel.setText(doubNewCoins.toString());
        AddCoinsTextField.setText("");
    }

    private String pickUpAddress(){
        String pickUpStreetAddress = StreetAddressTextField.getText();
        String pickUpCity = CityTextField.getText();
        String pickUpZip = ZipcodeTextField.getText();
        String pickUpState = OACPickStateDropBox.getValue();

        String finalPickupAddress = pickUpStreetAddress + "\n" + pickUpCity + ", " + pickUpZip + ", " + pickUpState;
        //test
//        System.out.println(finalPickupAddress);
        return finalPickupAddress;
    }

    private String destinationAddress(){
        String destStreetAddress = destStreetAddressTextField.getText();
        String destCity = destCityTextField.getText();
        String destZip = destZipcodeTextField.getText();
        String destState = OACDestStateDropBox.getValue();

        String finalDestAddress = destStreetAddress + "\n" + destCity + ", " + destZip + ", " + destState;
        //test
//        System.out.println(finalDestAddress);
        return finalDestAddress;
    }

    private String name(String firstName, String lastName){
        String name = firstName + " " + lastName;
        return name;

    }

    private String riderChoices(){
        String vehicleType = OACVehicleTypeDropBox.getValue();
        int numOfRiders = NumOfRidersDropBox.getValue();
        int driverRating = OACDriverRatingDropBox.getValue();

        String finalRiderChoices = vehicleType + ", " + numOfRiders + ", " + driverRating;
        //test
//        System.out.println(finalRiderChoices);
        return finalRiderChoices;
    }

    private String noDate(){
        LocalDate localdate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/YYYY");
        String date = formatter.format(localdate);
        //test
//        System.out.println(date);
        return date;
    }
    private String laterDate(String month, String day, String year){
        String date = "";
        if(dateMonth.getText().isEmpty() || dateDay.getText().isEmpty()){
            date = noDate().toString();
        }
        else{
            date = month + "/" + day + "/" + year;
        }
//        System.out.println(date);
        return date;
    }

    private String noTime(){
        LocalTime localTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        String time = formatter.format(localTime);
        //test
//        System.out.println(time);
        return time;
    }
    private String laterTime(String hour, String minute, String amORpm){
        String time = "";
        if(timeHour.getText().isEmpty() || timeMinute.getText().isEmpty() || AMorPM.getValue().isEmpty()){
            time = noTime().toString();
        }
        else{
            time = hour + ":" + minute + " " + amORpm;
        }
//        System.out.println(time);
        return time;
    };



    public void placeOrder(){
//        dateColumn.setCellValueFactory(new PropertyValueFactory<>(laterDate(dateMonth.getText(), dateDay.getText(), dateYear.getText())));
//        pickupColumn.setCellValueFactory(new PropertyValueFactory<>(pickUpAddress()));
//        destinationColumn.setCellValueFactory(new PropertyValueFactory<>(destinationAddress()));
//        //need to figure out what to do about cost.
//        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>(name(firstName.getText(), lastName.getText())));

        pickupColumn.setCellValueFactory(new PropertyValueFactory<>("pickupAddress"));
        destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destinationAddress"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

//        historyTable.setItems(getRide());
        getRide();
        StreetAddressTextField.setText("");
        CityTextField.setText("");
        OACPickStateDropBox.setValue("FL");
        ZipcodeTextField.setText("");

        destStreetAddressTextField.setText("");
        destCityTextField.setText("");
        OACDestStateDropBox.setValue("FL");
        destZipcodeTextField.setText("");

        OACVehicleTypeDropBox.setValue("Any");
        NumOfRidersDropBox.setValue(1);
        OACDriverRatingDropBox.setValue(5);

        dateMonth.setText("");;
        dateDay.setText("");;
        dateYear.setText("2019");;

        timeHour.setText("");
        timeMinute.setText("");
        AMorPM.setValue("");

    }

    private void getRide(){
        double cost = 0.0;

        RideHistory history = new RideHistory(name
                (firstName.getText(), lastName.getText()), pickUpAddress(), destinationAddress(), cost,
                laterDate(dateMonth.getText(), dateDay.getText(), dateYear.getText() + "\n "
                        + laterTime(timeHour.getText(), timeMinute.getText(), AMorPM.getValue())
                ));
//        double cost = history.getCost();

        historyTable.getItems().add(history);
    }

    public String riderRatingCalc(String newRiderRating){
        String finalRating = "";
        String riderRating = RiderRatingLabel.getText();
        double doubRiderRating = Double.parseDouble(riderRating);

        double newDoubRiderRating = Double.parseDouble(newRiderRating);
        Double finalDoubRiderRating =  (doubRiderRating + newDoubRiderRating) / 2;

//        RiderRatingLabel.setText(finalDoubRiderRating.toString());
        return finalRating = finalDoubRiderRating.toString();

    }

    public void quickCar(){
        StreetAddressTextField.setText(PrefStreetAddressTextField.getText());
        CityTextField.setText(PrefCityTextField.getText());
        OACPickStateDropBox.setValue(PrefStateDropBox.getValue());
        ZipcodeTextField.setText(PrefZipcodeTextField.getText());

        destStreetAddressTextField.setText(PrefDestStreetAddressTextField.getText());
        destCityTextField.setText(PrefDestCityTextField.getText());
        OACDestStateDropBox.setValue(PrefDestStateDropBox.getValue());
        destZipcodeTextField.setText(PrefDestZipcodeTextField.getText());
    }

//    public void historySubmit(){
//        if(pickupHistory.getItems() != null) {
////            String pickup = pickupHistory.getItems();
//            PrefStreetAddressTextField.setText(pickupHistory.getSelectionModel().getSelectedItem());
//        }
//    }

    public void splitPickUpAddress(RideHistory address){
        ObservableList<RideHistory> addressList;
        addressList = historyTable.getSelectionModel().getSelectedItems();
        //test
//        System.out.println("\n\n" + addressList.get(0).getPickupAddress() );

        String puAddress = addressList.get(0).getPickupAddress();
        String[] splitUpAddress = puAddress.split("\n", 2);

        String puStreetAddress = splitUpAddress[0];
        String cityStateZip = splitUpAddress[1];

//        System.out.println(splitUpAddress.length);
//        System.out.println("\n\n" +puStreetAddress);
//        System.out.println("\n" + cityStateZip);

        String[] splitCityStateZip = cityStateZip.split(", ", 3);
        String puCity = splitCityStateZip[0];
        String puState = splitCityStateZip[2];
        String puZip = splitCityStateZip[1];

//        System.out.println("\n\n" + splitCityStateZip.length);
//        System.out.println(puCity + "\n" + puState + " \n" + puZip);

        PrefStreetAddressTextField.setText(puStreetAddress);
        PrefCityTextField.setText(puCity);
        PrefStateDropBox.setValue(puState);
        PrefZipcodeTextField.setText(puZip);
    }

    public void splitDestinationAddress(RideHistory address){
        ObservableList<RideHistory> addressList;
        addressList = historyTable.getSelectionModel().getSelectedItems();

        String destAddress = addressList.get(0).getDestinationAddress();
        String[] splitUpDestAddress = destAddress.split("\n", 2);

        String destStreetAddress = splitUpDestAddress[0];
        String destCityStateZip = splitUpDestAddress[1];

        String[] splitCityStateZip = destCityStateZip.split(", ", 3);
        String destCity = splitCityStateZip[0];
        String destState = splitCityStateZip[2];
        String destZip = splitCityStateZip[1];

        PrefDestStreetAddressTextField.setText(destStreetAddress);
        PrefDestCityTextField.setText(destCity);
        PrefDestStateDropBox.setValue(destState);
        PrefDestZipcodeTextField.setText(destZip);
    }

    public void riderHistoryClick(){
        splitPickUpAddress(historyTable.getSelectionModel().getSelectedItem());
        splitDestinationAddress(historyTable.getSelectionModel().getSelectedItem());
    }

    public void logOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene logInScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(logInScene);
        window.show();
    }
















}
