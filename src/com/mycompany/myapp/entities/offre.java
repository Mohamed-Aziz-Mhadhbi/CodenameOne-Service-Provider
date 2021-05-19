/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author bhk
 */
public class offre {
  private int id;
    private String Title;
    private String Description;
    private int DomainOffre;
   // private Date CreatAt;
    private Double rating;

    public offre(int id, Double rating) {
        this.id = id;
        this.rating = rating;
    }

    public offre(String Title, String Description, int DomainOffre) {
        
        this.Title = Title;
        this.Description = Description;
        this.DomainOffre = DomainOffre;
    }

     public offre(String Title, String Description) {
      
        this.Title = Title;
        this.Description = Description;
       
       
    }

    /*public Offre(int i, String Title, String Description, int DomainOffre) {
       this.id = id;
        this.Title = Title;
        this.Description = Description;
        this.DomainOffre = DomainOffre;
    }*/
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    /*  public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }*/
   /* public void setCreatAt(Date CreatAt) {
        this.CreatAt = CreatAt;
    }

    public Date getCreatAt() {
        return CreatAt;
    }*/

    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public int getDomainOffre() {
        return DomainOffre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setDomainOffre(int DomainOffre) {
        this.DomainOffre = DomainOffre;
    }

    public offre() {
    }


    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", Title=" + Title + ", Description=" + Description + ", DomainOffre=" + DomainOffre + ", rating=" + rating + '}';
    }

    
    
}
