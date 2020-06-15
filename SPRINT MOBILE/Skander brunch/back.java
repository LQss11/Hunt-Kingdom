/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author skand
 */
public class back {
     private double prixTOT;
    private String nomProd;
    private double Quantity;
    private double prixTTC;

    @Override
    public String toString() {
        return "back{" + "prixTOT=" + prixTOT + ", nomProd=" + nomProd + ", Quantity=" + Quantity + ", prixTTC=" + prixTTC + '}';
    }

    public back() {
    }

    public double getPrixTOT() {
        return prixTOT;
    }

    public void setPrixTOT(double prixTOT) {
        this.prixTOT = prixTOT;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double Quantity) {
        this.Quantity = Quantity;
    }

    public double getPrixTTC() {
        return prixTTC;
    }

    public void setPrixTTC(double prixTTC) {
        this.prixTTC = prixTTC;
    }

  

    
}
