/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


/**
 *
 * @author aziz9
 */
public class Evenement implements java.io.Serializable
{
     private int id;
    private String nom;
    private String type;
    private double prix;
    private int nbrplace;
    private String date;
    private String place;
    private String description;
    private int duree;
    private String image;

    public Evenement(String nomm, String typem, Double prixm, int nbrplacem, String placem, String descriptionm, int dureem, String imagem) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.nbrplace = nbrplace;
        this.place = place;
        this.description = description;
        this.duree = duree;
        this.image = image;
    }
    
    @Override
    public String toString() 
    {
        return "Evenement{" + "id=" + id+ "nom=" + nom+ "type=" + type+ "prix=" + prix+ "nbrplace=" + nbrplace+ "date=" + date+ "place=" + place+ "description=" + description+ "duree=" + duree+ ", image=" + image +'}' ;
        
    }

    public Evenement()
    {
    }
    
    public Evenement(int id, String nom, String type, double prix, int nbrplace, String date, String place, String description, int duree, String image) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.nbrplace = nbrplace;
        this.date = date;
        this.place = place;
        this.description = description;
        this.duree = duree;
        this.image = image;
    }

    
    
    public Evenement(String nom, String type, double prix, int nbrplace,String date,String place, String description, int duree, String image) {
        this.nom = nom;
        this.type = type;
        this.prix = prix;
        this.nbrplace = nbrplace;
        this.date = date;
        this.place = place;
        this.description = description;
        this.duree = duree;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbrplace() {
        return nbrplace;
    }

    public void setNbrplace(int nbrplace) {
        this.nbrplace = nbrplace;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
}
