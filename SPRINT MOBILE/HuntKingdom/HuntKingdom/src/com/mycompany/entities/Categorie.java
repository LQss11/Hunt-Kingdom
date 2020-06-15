/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;



/**
 *
 * @author khalil
 */
public class Categorie {

     
 private int id;
    private String nom;
    private String description;
    private String image;

    public Categorie() {
    }

    public Categorie(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Categorie(String nom, String description, String image) {
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
 public Categorie(int id, String nom, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.image = image;
    }
 
    @Override
    public String toString() {
        return "" + nom  ;
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
   
    
}
