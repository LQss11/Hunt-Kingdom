/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author LQss
 */
public class Reclamation {

    private int id;
    private String type;
    private int ido;
    private String sujet;
    private String description;
    private String date;
    private String etat;
    private int idu;

    public Reclamation(String type, String sujet, String description, String etat) {
        this.type = type;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
    }

    public Reclamation() {
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", ido=" + ido + ", sujet=" + sujet + ", description=" + description + ", date=" + date + ", etat=" + etat + ", idu=" + idu + '}';
    }

    public int getId() {
        return id;
    }

    public Reclamation(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reclamation(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdo() {
        return ido;
    }

    public void setIdo(int ido) {
        this.ido = ido;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getIdu() {
        return idu;
    }

    public void setIdu(int idu) {
        this.idu = idu;
    }

    public Reclamation(int id, String type, int ido, String sujet, String description, String date, String etat, int idu) {
        this.id = id;
        this.type = type;
        this.ido = ido;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.idu = idu;
    }

}
