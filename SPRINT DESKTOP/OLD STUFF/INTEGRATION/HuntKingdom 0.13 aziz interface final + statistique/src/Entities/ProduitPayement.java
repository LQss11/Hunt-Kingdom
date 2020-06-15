/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author skand
 */
public class ProduitPayement {

    private String reference;
    private int quantite;
    private double prixUni;

    public ProduitPayement(String reference) {
        this.reference = reference;
    }

    public ProduitPayement() {
    }

    @Override
    public String toString() {
        return "ProduitPayement{" + "reference=" + reference + ", quantite=" + quantite + ", prixUni=" + prixUni + ", prixTotal=" + prixTotal + ", prixAll=" + prixAll + '}';
    }
    private double prixTotal;
    private double prixAll;

    public ProduitPayement(String reference, int quantite, double prixUni, double prixTotal) {
        this.reference = reference;
        this.quantite = quantite;
        this.prixUni = prixUni;
        this.prixTotal = prixTotal;
    }

    public ProduitPayement(String reference, int quantite, double prixUni, double prixTotal, double prixAll) {
        this.reference = reference;
        this.quantite = quantite;
        this.prixUni = prixUni;
        this.prixTotal = prixTotal;
        this.prixAll = prixAll;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUni() {
        return prixUni;
    }

    public void setPrixUni(double prixUni) {
        this.prixUni = prixUni;
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(double prixTotal) {
        this.prixTotal = prixTotal;
    }

    public double getPrixAll() {
        return prixAll;
    }

    public void setPrixAll(double prixAll) {
        this.prixAll = prixAll;
    }

}
