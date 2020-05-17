/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;
import java.util.Date;
/**
 *
 * @author khalil
 */
public class Promotion {
     private int id ;
    private int pourcentage;
    private Produit Produit;
    private Date dateFin;
    private Date dateDebut;
    private float prix;
    private int active;

    public Promotion(int pourcentage, Produit Produit, Date dateFin, Date dateDebut,  int active) {
        this.pourcentage = pourcentage;
        this.Produit = Produit;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.active = active;
    }
  

    public Promotion(int id, int pourcentage, Produit Produit, Date dateFin, Date dateDebut) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.Produit = Produit;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
    }

    public Promotion(int id, int pourcentage, Date dateFin, Date dateDebut, int active,Produit p) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.active = active;
    
        this.Produit=p;
    }

    public Promotion(int id, int pourcentage, Produit Produit, Date dateFin, Date dateDebut, float prix, int active) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.Produit = Produit;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.prix = prix;
        this.active = active;
    }

    public Promotion(int id, int pourcentage, Produit Produit, Date dateFin, Date dateDebut, float prix) {
        this.id = id;
        this.pourcentage = pourcentage;
        this.Produit = Produit;
        this.dateFin = dateFin;
        this.dateDebut = dateDebut;
        this.prix = prix;
    }

    public Promotion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(int pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Produit getProduit() {
        return Produit;
    }

    public void setProduit(Produit Produit) {
        this.Produit = Produit;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", pourcentage=" + pourcentage + ", Produit=" + Produit + ", dateFin=" + dateFin + ", dateDebut=" + dateDebut + ", prix=" + prix + ", active=" + active + '}';
    }

    
}
