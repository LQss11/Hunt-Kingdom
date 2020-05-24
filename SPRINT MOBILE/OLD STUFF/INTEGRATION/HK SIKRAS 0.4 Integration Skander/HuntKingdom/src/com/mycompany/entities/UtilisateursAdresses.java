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
public class UtilisateursAdresses {

    private int id;
    private int utilisateur_id;
    private String nom;
    private String prenom;
    private String telephone;
    private String adresse;
    private String cp;
    private String pays;
    private String ville;
    private String complement;

    public UtilisateursAdresses(int id, int utilisateur_id, String nom, String prenom, String telephone, String adresse, String cp, String pays, String ville, String complement) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.cp = cp;
        this.pays = pays;
        this.ville = ville;
        this.complement = complement;
    }

    public UtilisateursAdresses(int utilisateur_id, String nom, String prenom, String telephone, String adresse, String cp, String pays, String ville, String complement) {
        this.utilisateur_id = utilisateur_id;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.cp = cp;
        this.pays = pays;
        this.ville = ville;
        this.complement = complement;
    }

    public UtilisateursAdresses(String nom, String prenom, String telephone, String adresse, String cp, String pays, String ville, String complement) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.cp = cp;
        this.pays = pays;
        this.ville = ville;
        this.complement = complement;
    }

    public UtilisateursAdresses() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public void setUtilisateur_id(int utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

}
