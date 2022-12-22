package com.example.lab3.things.controller;

import java.sql.*;

public class SendVariableToDB {

    public static void sendToDB(String argument) throws SQLException, ClassNotFoundException {
// Replace with your own connection details
        String url = "jdbc:mysql://host.docker.internal:3306/mydatabase";
        String username = "root";
        String password = "password";

        try {
            // Connect to the database
            Connection conn = DriverManager.getConnection(url, username, password);

            // Create the SQL statement
            String sql = "INSERT INTO users (name) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the variable value as a parameter
            stmt.setString(1, argument);

            // Execute the statement
            stmt.executeUpdate();

            // Close the statement and connection
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}