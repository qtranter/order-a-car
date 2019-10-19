package sample;

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
            rs = stmt.executeQuery("SELECT * FROM driver;");

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

    public void insert(String table, int id, String first_name, String last_name) {
        try {
            String sql = "INSERT INTO " + table + " VALUES (?, ?, ?)";

            PreparedStatement insertUser = con.prepareStatement(sql);

            insertUser.setInt(1, id);
            insertUser.setString(2, first_name);
            insertUser.setString(3, last_name);

            insertUser.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validate(String userName, String password) {
        try {
            String sql = "SELECT * FROM DRIVER WHERE FIRST_NAME = ? and last_name = ?";
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

    public void sqlExceptionHandler(SQLException error) {
        System.out.println("Standard Failure: " + error.getMessage());
    }
}
