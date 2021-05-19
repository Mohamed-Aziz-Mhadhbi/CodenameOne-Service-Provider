/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Nour
 */

import java.util.Date;

public class Service {
     private String prix ;
    private String title , description , image;
    private String creat_at ;
    private int id;

    public Service() {
    }

    public Service(int id, String prix, String title, String description, String image, String creat_at) {
        this.id = id;
        this.prix = prix;
        this.title = title;
        this.description = description;
        this.image = image;
        this.creat_at = creat_at;
    }
     public Service(int id, String title, String description, String prix, String creat_at) {
        this.id = id;
        
        this.title = title;
        this.description = description;
        this.prix = prix;
        this.creat_at = creat_at;
    }

    public Service( String title, String description, String prix, String creat_at) {
        this.prix = prix;
        this.title = title;
        this.description = description;
        this.creat_at = creat_at;
    }
     

    @Override
    public String toString() {
        return "Service{" + "prix=" + prix + ", title=" + title + ", description=" + description + ", image=" + image + ", creat_at=" + creat_at + ", id=" + id + '}';
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreat_at() {
        return creat_at;
    }

    public void setCreat_at(String creat_at) {
        this.creat_at = creat_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
