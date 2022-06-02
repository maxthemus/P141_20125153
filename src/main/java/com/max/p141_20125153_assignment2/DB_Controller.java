/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Maxth
 */
public class DB_Controller {
    //Fields
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    
    
    private static final String URL = "jdbc:derby:chessDB;create=true";
    
    Connection DB_conneciton;
    
    
    //Constructor
    public DB_Controller() {
        this.createConnectionToDB();
    }
    
    //Methods
    public Connection getConnection() {
        return this.DB_conneciton;
    }
    
    private void createConnectionToDB() {        
        if(this.DB_conneciton == null) {
            try {
                this.DB_conneciton = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            } catch(SQLException e) {
                System.out.println("DB CONNECITON FAILED PLEASE RESTART THE DB");
            }
        }
    }
    
    public void closeConection() {
        if(this.DB_conneciton != null) {
            try {
                this.DB_conneciton.close();
            } catch (SQLException ex) {
                System.out.println("FAILED TO CLOSE CONNECTION TO DB");
            }
        }
    }
    
    public ResultSet myQuery(String sql) {
        Connection tempConneciton = this.DB_conneciton;
        Statement tempStatement = null;
        ResultSet tempResultSet = null;
        
        try {
            tempStatement = tempConneciton.createStatement();
            tempResultSet = tempStatement.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("SOMETHING WENT WRONG WITH THE QUERY");
        }
        
        return tempResultSet;
    }
    
    public void myUpdate(String sql) {

        Connection connection = this.DB_conneciton;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
