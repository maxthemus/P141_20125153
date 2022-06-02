/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.max.p141_20125153_assignment2;

import java.util.ArrayList;

/**
 *
 * @author Maxth
 */
public class UserTempStorage {
    //Fields
    private ArrayList<User> tempUsers;
    private final String[] tempNames = {"Maxthemus", "Jamie", "Jef", "Tom", "Paul"};
    
    //Constructor
    public UserTempStorage() {
        for(int i = 0; i < 5; i++) {
            this.tempUsers.add(new User(i, this.tempNames[i], "root", (int)(Math.random() % 5)));
        }
        
    }
    
    //Methods
    public void sortUsers () {
        for(int i = 0; i < this.tempUsers.size() - 1; i++) {
            for(int j = 0; j < this.tempUsers.size() - i - 1; i++) {
                User tempUser = this.tempUsers.get(j);
                this.tempUsers.set(j, this.tempUsers.get(j + 1));
                this.tempUsers.set(j + 1, tempUser);
            }
        }
    }
    
    public ArrayList<User> getUsers () {
        return this.tempUsers;
    }
}
