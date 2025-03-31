package client.servicerestclient.wrk;

import client.servicerestclient.beans.User;

import java.sql.*;

public class WrkDB {
    private final String URL = "jdbc:mysql://localhost:3308/mydb";
    private final String USER = "root";
    private final String PASSWORD = "emf123";

    public boolean readUser(String login) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "SELECT * FROM T_Users WHERE login = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
                        }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; 
    }
}
