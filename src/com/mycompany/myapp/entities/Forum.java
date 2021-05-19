/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.ArrayList;

/**
 *
 * @author bhk
 */
public class Forum {

    private int id,idC,idR;
    private String title, description;
    private ArrayList<Post> Posts;

    public Forum() {
    }

    public Forum(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Forum(int id, int idC, String title, String description) {
        this.id = id;
        this.idC = idC;
        this.title = title;
        this.description = description;
    }

    public Forum(int id, String title, String description, ArrayList<Post> Posts) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.Posts = Posts;
    }
public Forum(int id, String title, String description, int idR) {
        this.id = id;
        this.idR = idR;
        this.title = title;
        this.description = description;
    }
    public Forum(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Forum(String title, String description,int id ) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public ArrayList<Post> getPosts() {
        return Posts;
    }

    public void setPosts(ArrayList<Post> Posts) {
        this.Posts = Posts;
    }

    @Override
    public String toString() {
        return "Forum{" + "id=" + id + ", idC=" + idC + ", idR=" + idR + ", title=" + title + ", description=" + description + '}';
    }

   

}
