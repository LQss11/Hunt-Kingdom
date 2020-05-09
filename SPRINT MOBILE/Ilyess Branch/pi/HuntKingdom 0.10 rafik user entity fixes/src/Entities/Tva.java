/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author khalil
 */
public class Tva implements Serializable{
    private int id ;
    private double multiplicate;
    private String  nom;
    private double valeur;

    public Tva(int id, double multiplicate, String nom, double valeur) {
        this.id = id;
        this.multiplicate = multiplicate;
        this.nom = nom;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMultiplicate() {
        return multiplicate;
    }

    @Override
    public String toString() {
        return "Tva{" + "id=" + id + ", multiplicate=" + multiplicate + ", nom=" + nom + ", valeur=" + valeur + '}';
    }

    public void setMultiplicate(double multiplicate) {
        this.multiplicate = multiplicate;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    

}
