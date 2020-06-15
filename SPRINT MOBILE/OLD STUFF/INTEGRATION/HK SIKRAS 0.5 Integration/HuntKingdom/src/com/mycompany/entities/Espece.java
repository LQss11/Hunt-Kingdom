/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ilyess
 */
public class Espece {
     private  int idEspece;
    private String nomEspece;
    private String descriptionEspece;
    private String image;
    private String poids;
     private String type;
    private String zone;
    private String ville;
    private int idS;

    
    public Espece(int idEspece, String nomEspece, String descriptionEspece, String image, String poids, String type, String zone, String ville, int idS) {
        this.idEspece = idEspece;
        this.nomEspece = nomEspece;
        this.descriptionEspece = descriptionEspece;
        this.image = image;
        this.poids = poids;
        this.type = type;
        this.zone = zone;
        this.ville = ville;
        this.idS = idS;
    }

    public Espece(String nomEspece, String descriptionEspece, String image, String poids, String type, String zone, String ville, int idS) {
        this.nomEspece = nomEspece;
        this.descriptionEspece = descriptionEspece;
        this.image = image;
        this.poids = poids;
        this.type = type;
        this.zone = zone;
        this.ville = ville;
        this.idS = idS;
    }
    public Espece(String nomEspece, String descriptionEspece, String image, String poids, String type, String zone, String ville) {
        this.nomEspece = nomEspece;
        this.descriptionEspece = descriptionEspece;
        this.image = image;
        this.poids = poids;
        this.type = type;
        this.zone = zone;
        this.ville = ville;
       
    }
    public Espece() {
            }

    public int getIdEspece() {
        return idEspece;
    }

    public void setIdEspece(int idEspece) {
        this.idEspece = idEspece;
    }

    public String getNomEspece() {
        return nomEspece;
    }

    public void setNomEspece(String nomEspece) {
        this.nomEspece = nomEspece;
    }

    public String getDescriptionEspece() {
        return descriptionEspece;
    }

    public void setDescriptionEspece(String descriptionEspece) {
        this.descriptionEspece = descriptionEspece;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPoids() {
        return poids;
    }

    public void setPoids(String poids) {
        this.poids = poids;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public int getIdS() {
        return idS;
    }

    public void setIdS(int idS) {
        this.idS = idS;
    }

    @Override
    public String toString() {
        return "Espece{" + "nomEspece=" + nomEspece + ", descriptionEspece=" + descriptionEspece + ", image=" + image + ", poids=" + poids + ", type=" + type + ", zone=" + zone + ", ville=" + ville + ", idS=" + idS + '}';
    }

    
    
    
}
