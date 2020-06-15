/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author ilyess
 */
public class Saison {
     private int idSaison;
    private String nom;
    private String periode;
    private String mode;

    public Saison(int idSaison, String nom, String periode, String mode) {
        this.idSaison = idSaison;
        this.nom = nom;
        this.periode = periode;
        this.mode = mode;
    }

    public Saison(String nom, String periode, String mode) {
        this.nom = nom;
        this.periode = periode;
        this.mode = mode;
    }

    public Saison() {
       
    }

    @Override
    public String toString() {
        return "Saison{" + "nom=" + nom + ", periode=" + periode + ", mode=" + mode + "}\n";
    }

    public int getIdSaison() {
        return idSaison;
    }

    public String toString1() {
        return "Saison{" + "nom=" + nom + ", periode=" + periode + ", mode=" + mode + '}';
    }

    public void setIdSaison(int idSaison) {
        this.idSaison = idSaison;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
    
}
