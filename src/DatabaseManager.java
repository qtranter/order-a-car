import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:C:/Users/kharn/IdeaProjects/order-a-car/res/userDatabase";
    private Connection con;

    //URL: jdbc:h2:C:/Users/kharn/IdeaProjects/order-a-car/res/userDatabase

    public DatabaseManager() throws SQLException {
        con = DriverManager.getConnection(DB_URL, "sa", "");
    }

    public int getUserID(String username, String password) throws SQLException {
        String sql = "SELECT ID FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        int userID = -1;
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            userID = rs.getInt("ID");
        }
        return userID;
    }

    public String getUserName(int ID) throws SQLException {
        String sql = "SELECT USERNAME FROM USER WHERE ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, ID);

        String userName = "";
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            userName = rs.getString("USERNAME");
        }
        return userName;
    }
    public String getFirstName(int ID) throws SQLException {
        String sql = "SELECT FIRST_NAME FROM USER WHERE ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, ID);

        String firstName = "";
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            firstName = rs.getString("FIRST_NAME");
        }
        return firstName;
    }
    public String getLastName(int ID) throws SQLException {
        String sql = "SELECT LAST_NAME FROM USER WHERE ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, ID);

        String lastName = "";
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            lastName = rs.getString("LAST_NAME");
        }
        return lastName;
    }

    public String getCoins(int ID) throws SQLException {
        String sql = "SELECT COINS FROM USER WHERE ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, ID);

        double coins = -1;
        String coinString;
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            coins = rs.getDouble("COINS");
        }
        coinString = coins+"";
        return coinString;
    }

    public String getRating(int ID) throws SQLException {
        String sql = "SELECT RATING FROM USER WHERE ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, ID);

        int rating = -1;
        String ratingString;
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            rating = rs.getInt("RATING");
        }
        ratingString = rating+"";
        return ratingString;
    }



    public void insertUser(String first_name, String last_name, String username, String password, int rating, float coins, int user_type) {
        try {
            String sql = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insertUser = con.prepareStatement(sql);

            insertUser.setString(1, null);
            insertUser.setString(2, first_name);
            insertUser.setString(3, last_name);
            insertUser.setString(4, username);
            insertUser.setString(5, password);
            insertUser.setInt(6, rating);
            insertUser.setFloat(7, coins);
            insertUser.setInt(8, user_type);

            insertUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertRide(int userID, String pickup, String destination) {
        try {
            String sql = "INSERT INTO RIDES VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement insertRide = con.prepareStatement(sql);

            insertRide.setInt(1, userID);
            insertRide.setString(2, pickup);
            insertRide.setString(3, destination);
            insertRide.setDouble(4, 0.00);
            DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault());
            insertRide.setString(5, DATE_TIME_FORMATTER.format(Instant.now()));
            insertRide.setBoolean(6, false);

            insertRide.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Will only go to the next scene if the user has already created an account. This is the first step
    //in determining which scene to send the user
    public boolean userExists(String userName, String password) {
        try {
            String sql = "SELECT * FROM USER WHERE USERNAME = ? and PASSWORD = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }

        } catch (SQLException e) {
            // print SQL exception information
            sqlExceptionHandler(e);
        }
        return false;
    }

    public int userType(String username, String password) throws SQLException {
        String sql = "SELECT USER_TYPE FROM USER WHERE USERNAME = ? AND PASSWORD = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        int riderType = -1;
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
            riderType = rs.getInt("USER_TYPE");
        }
        return riderType;
    }

    public void sqlExceptionHandler(SQLException error) {
        System.out.println("Standard Failure: " + error.getMessage());
    }

    public List<Ride> getAvailableRides() throws SQLException {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT NAME, PICKUP_ADDRESS, DESTINATION_ADDRESS, COST, DATE FROM RIDES WHERE COMPLETED = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setBoolean(1, false);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Ride ride = new Ride(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5)
            );
            rides.add(ride);
        }
        return rides;
    }

    public List<Ride> getRides(int userID) throws SQLException {
        List<Ride> rides = new ArrayList<>();
        String sql = "SELECT NAME, PICKUP_ADDRESS, DESTINATION_ADDRESS, COST, DATE FROM RIDES WHERE USER_ID = ?";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setInt(1, userID);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Ride ride = new Ride(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getString(5)
            );
            rides.add(ride);
        }
        return rides;
    }
}
