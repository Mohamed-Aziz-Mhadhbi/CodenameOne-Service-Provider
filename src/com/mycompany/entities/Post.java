/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public class Post {

    private int id;
    private String title;
    private String description;
    private int views;
    private int noc;
    private String date;
    private int forum_id;
    private Forum forum;
    private int idC;
    private int idR;
    private ArrayList<Comment> Comments;

    public Post() {
    }

    public Post(int id, String title, String description, int views, int noc, String date, int forum_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.date = date;
        this.forum_id = forum_id;
    }

    public Post(String title, String description, int forum_id) {
        this.title = title;
        this.description = description;
        this.forum_id = forum_id;
    }

    public Post(int id, String title, String description, int views, int noc, String date, int idF, int idC, int idR) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.date = date;
        this.forum_id = forum_id;
        this.idC = idC;
        this.idR = idR;
    }

    public Post(int id, String title, int idC, String description, int views, int noc, String date, int forum_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.date = date;
        this.forum_id = forum_id;
        this.idC = idC;
    }

    public Post(int id, String title, String description, int views, int noc, String date, int forum_id, int idR) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.date = date;
        this.forum_id = forum_id;
        this.idR = idR;
    }

    public Post(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Post(int id, String title, String description, int noc) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.noc = noc;
    }

    public Post(int id, String title, String description, int views, int noc) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
    }

    public Post(int id, String title, String description, int views, int noc, String date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.date = date;
    }

    public Post(String title, String description, int views, int noc, String date) {
        this.title = title;
        this.description = description;
        this.views = views;
        this.noc = noc;
        this.date = date;
    }

    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public int getForum_id() {
        return forum_id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
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

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getNoc() {
        return noc;
    }

    public void setNoc(int noc) {
        this.noc = noc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
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

    public ArrayList<Comment> getComments() {
        return Comments;
    }

    public void setComments(ArrayList<Comment> Comments) {
        this.Comments = Comments;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", title=" + title + ", description=" + description + ", views=" + views + ", noc=" + noc + ", date=" + date + ", idF=" + forum_id + ", forum=" + forum + ", idC=" + idC + ", idR=" + idR + '}';
    }

}
