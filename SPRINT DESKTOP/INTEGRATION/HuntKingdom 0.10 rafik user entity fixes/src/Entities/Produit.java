/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author khalil
 */
public class Produit {
      
    private int id;
    private  Categorie categorie;
    private String nom;
    private int quantite;
    private float prix;
    private String description;
    private int idFournisseur;
    private int etatPromo;
    private int garantie;
    private int tvaId;
    private String image;
    private Promotion promotion;

    private ImageView im;

    public Produit(int id, String nom, int quantite, float prix, String description, int etatPromo, int garantie, String image, Promotion promotion) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.description = description;
        this.etatPromo = etatPromo;
        this.garantie = garantie;
        this.image = image;
        this.promotion = promotion;
    }

    public ImageView getIm() {
        return im;
    }

    public void setIm(ImageView im) {
        this.im = im;
    }
    //for affichage
    private String nomCat;

    public String getNomCat() {
        return nomCat;
    }

    public Produit(int id, Categorie categorie, String nom, int quantite, float prix, String description, int garantie) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.description = description;
        this.garantie = garantie;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }
    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }
    public Produit() {
     
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id  +
                ", nom=" + nom + ", quantite=" + quantite + ", prix=" + prix + 
                ", description=" + description + ", etatPromo=" + etatPromo + 
                ", garantie=" + garantie + ", image=" + image +'}' ;
        
    }

    public Produit(int id, Categorie categorie, String nom, int quantite, float prix, String description, int garantie, String image) {
        this.id = id;
        this.categorie = categorie;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.description = description;
        this.garantie = garantie;
        this.image = image;
    }

    public Produit(Categorie categorie, String nom, int quantite, float prix, String description, int garantie, String image) {
        this.categorie = categorie;
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.description = description;
        this.garantie = garantie;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(int idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public int getEtatPromo() {
        return etatPromo;
    }

    public void setEtatPromo(int etatPromo) {
        this.etatPromo = etatPromo;
    }

    public int getGarantie() {
        return garantie;
    }

    public void setGarantie(int garantie) {
        this.garantie = garantie;
    }

    public int getTvaId() {
        return tvaId;
    }

    public void setTvaId(int tvaId) {
        this.tvaId = tvaId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Produit(String nom, float prix, String description, int garantie, String nomCat) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.garantie = garantie;
        this.nomCat = nomCat;
    } 
}
