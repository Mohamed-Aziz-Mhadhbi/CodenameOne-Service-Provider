/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author youss
 */
public class User {
        private int id;
	private String userName;
	private String userLastname;
	private String userRole;
	private String userEmail;
	private String userPhone;
	private String password;
	private String userPhoto;
        private String userGender;

    public User() {
    }
        
    

    public User(int id, String userName, String userLastname, String userRole, String userEmail, String userPhone, String password, String userPhoto, String userGender) {
        this.id = id;
        this.userName = userName;
        this.userLastname = userLastname;
        this.userRole = userRole;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.password = password;
        this.userPhoto = userPhoto;
        this.userGender = userGender;
    }

    public User(String userName, String userLastname, String userRole, String userEmail, String userPhone, String password, String userPhoto, String userGender) {
        this.userName = userName;
        this.userLastname = userLastname;
        this.userRole = userRole;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.password = password;
        this.userPhoto = userPhoto;
        this.userGender = userGender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastname() {
        return userLastname;
    }

    public void setUserLastname(String userLastname) {
        this.userLastname = userLastname;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getUserGender() {
        return userGender;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }
    
    
    
    }
    
    
        

