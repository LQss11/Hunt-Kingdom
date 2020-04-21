/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author khalil
 */
public class Whishlist {
     private int id;
    private Produit produit;
    private Utilisateurs user;

    public Whishlist() {
    }

    public Whishlist(int id, Produit produit, Utilisateurs user) {
        this.id = id;
        this.produit = produit;
        this.user = user;
    }

    public Whishlist(Produit produit, Utilisateurs user) {
        this.produit = produit;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Utilisateurs getUser() {
        return user;
    }

    public void setUser(Utilisateurs user) {
        this.user = user;
    }
    
}
