/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ASUS
 */
public class Comment {
    int id , rating ;
    String content ; 
    String date; 

    public Comment() {
    }

    public Comment(int id, int rating, String content, String date) {
        this.id = id;
        this.rating = rating;
        this.content = content;
        this.date = date;
    }

    public Comment(String content, int rating,int id ) {
        this.id = id;
        this.rating = rating;
        this.content = content;
    }

    public Comment(int rating, String content) {
        this.rating = rating;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", rating=" + rating + ", content=" + content + ", date=" + date + '}';
    }
    
    
}
