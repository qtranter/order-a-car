import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;

public class DatabaseManager {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:D:/trant/Documents/Java Practice/Order A Car2/res/userDatabase";
    private Connection con;

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
            userID = rs.getInt("USER_TYPE");
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
        String sql = "SELECT USERNAME FROM USER WHERE ID = ?";
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



    public void insert(String first_name, String last_name, String username, String password, int rating, float coins, int user_type) {
        try {
            String sql = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insertUser = con.prepareStatement(sql);

            insertUser.setInt(1, 0);
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
}
