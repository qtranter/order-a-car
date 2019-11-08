package sample;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.sql.*;

public class DatabaseManager {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:D:/trant/Documents/Java Practice/Order A Car2/res/userDatabase";
    private Connection con;

    public DatabaseManager() throws SQLException {
        con = DriverManager.getConnection(DB_URL, "sa", "");
    }

    public void selectUser() {
        ResultSet rs;

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USER;");

            System.out.println("First Name: " + "Last Name: " + "Rating: " + "Address: ");

            while (rs.next()) {

                System.out.print(rs.getString("first_name") + "\t");
                System.out.print(rs.getString("last_name") + "\t\t");
                System.out.println(rs.getInt("rating") + "\t");
                System.out.println(rs.getString("address"));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    public void insert(String first_name, String last_name, String username, String password, int rating, float coins, String address, int user_type) {
        try {
            String sql = "INSERT INTO USER VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement insertUser = con.prepareStatement(sql);

            insertUser.setString(1, first_name);
            insertUser.setString(2, last_name);
            insertUser.setString(3, username);
            insertUser.setString(4, password);
            insertUser.setInt(5, rating);
            insertUser.setFloat(6, coins);
            insertUser.setString(7, address);
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
