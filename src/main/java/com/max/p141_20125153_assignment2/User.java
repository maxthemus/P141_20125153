/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

/**
 *
 * @author Maxth
 */
public class User implements Comparable<User>{
    public static int USER_ID_COUNT = 6;
    
    //Fields
    private final int USER_ID;
    private String username;
    private String password;
    private int WINS;
    
    
    //Constructor
    public User(int USER_ID, String username, String password, int WINS) {
        this.USER_ID = USER_ID;
        this.username = username;
        this.password = password;
        this.WINS = WINS;
    }

    
    
    
    //Methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getWINS() {
        return WINS;
    }

    public void setWINS(int WINS) {
        this.WINS = WINS;
    }

    public int getUSER_ID() {
        return USER_ID;
    }
    
    //ToString method
    @Override
    public String toString() {
        return "User{" + "username=" + username + ", password=" + password + ", WINS=" + WINS + '}';
    }
    
    
    //Implementing compareTo method
    @Override
    public int compareTo(User o) {
        if(this.getWINS() > o.getWINS()) {
            return 1;
        } else if (this.getWINS() < o.getWINS()) {
            return -1;
        } 
        return 0;
    }
    
}
