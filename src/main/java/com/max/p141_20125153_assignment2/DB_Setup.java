/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maxth
 */
public class DB_Setup {
    //Field
    public DB_Controller dbController;
    
    //Constructor
    public DB_Setup() {
        this.dbController = new DB_Controller();
    }
    
    //Methods
     public void createTable() {
        try {
            Statement statement = (this.dbController.getConnection()).createStatement();
                
            String tempTableStatement = "PLAYERS";
            String sqlQuery = "create table " + tempTableStatement + " (ID int not null,"
                    + "Name varchar(20), Password varchar(20),"
                    + "Wins int, PRIMARY KEY (ID))";
            
            try {
                statement.executeUpdate(sqlQuery);
            } catch (SQLException e) {
                System.out.println("Table already exists");
            } 
             
            try {
                String sqlInserts = "INSERT INTO " + tempTableStatement + " VALUES("
                + "1, 'Maxthemus', 'admin', 1)," +
                "(2, 'Jeff', 'admin', 1)," +
                "(3, 'Tom', 'admin', 1)," +
                "(4, 'Pual', 'admin', 1)" +
                "(5, 'Jamie', 'admin', 1)";
                statement.executeUpdate(sqlInserts);
            } catch(SQLException e) {};
           
            
        } catch (SQLException e) {
            System.out.println("COULDN'T CREATE TABLE PLEASE INSTALL DERBY CORRECTLY");
            System.out.println(e.toString());
        }
    }
    
    public void getQuery() {
        ResultSet rs = null;

        try {
            Statement statement = dbController.getConnection().createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String sqlQuery = "select id, name, password, wins from players";

            rs = statement.executeQuery(sqlQuery);
            rs.beforeFirst();
            
            while (rs.next()) {
                int id = rs.getInt(1); // 1
                String username = rs.getString(2);
                String password = rs.getString(3);
                int wins = rs.getInt(4);
                System.out.println(id + " : " + username + " : " + password + " : " + wins);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DB_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


