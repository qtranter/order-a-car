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

    public void selectAll() {
        ResultSet rs;

        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USER;");

            System.out.println("ID: " + "First Name: " + "Last Name:");

            while (rs.next()) {

                System.out.print(rs.getInt("id") + "\t");
                System.out.print(rs.getString("first_name") + "\t\t");
                System.out.println(rs.getString("last_name"));
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
    }

    public void insert(int id, String first_name, String last_name, int user_type, String username, String password) {
        try {
            //String sql = "INSERT INTO " + table + " VALUES (?, ?, ?)";
            String sql = "INSERT INTO USER VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement insertUser = con.prepareStatement(sql);

            insertUser.setInt(1, id);
            insertUser.setString(2, first_name);
            insertUser.setString(3, last_name);
            insertUser.setInt(4, user_type);
            insertUser.setString(5, username);
            insertUser.setString(6, password);

            insertUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Will only go to the next scene if the user has already created an account. This is the first step
    //in determining which scene to send the user
    public boolean validateLogin(String userName, String password) {
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
