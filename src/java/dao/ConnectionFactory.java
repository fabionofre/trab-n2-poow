/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fabin_000
 */
public class ConnectionFactory {
    public static Connection getConnection() throws ClassNotFoundException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb", "root", "root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
