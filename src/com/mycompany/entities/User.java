/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


public class User {
        private int id;
	private String userName;
	private String lastName;
        private String firstName;
	private String Role;
	private String Email;
	private int Phone;
	private String password;
	private String Photo;
        private String specialisation;

    public User() {
    }

    public User(int id, String userName, String lastName, String firstName, String Role, String Email, int Phone, String password, String Photo, String specialisation) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.Role = Role;
        this.Email = Email;
        this.Phone = Phone;
        this.password = password;
        this.Photo = Photo;
        this.specialisation = specialisation;
    }
    
    public User(String userName, String lastName, String firstName, String Role, String Email, int Phone, String password, String Photo, String specialisation) {
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.Role = Role;
        this.Email = Email;
        this.Phone = Phone;
        this.password = password;
        this.Photo = Photo;
        this.specialisation = specialisation;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int Phone) {
        this.Phone = Phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
        
     
    }
    
    
        

