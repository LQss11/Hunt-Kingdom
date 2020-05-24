/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author LQss
 */
public class Reclamation {

    public Reclamation(String type, String sujet, String description, Date date, String etat) {
        this.type = type;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }

    public Reclamation(String description) {
        this.description = description;
    }

    public Reclamation(int id, String etat) {
        this.id = id;
        this.etat = etat;
    }

    public Reclamation(int id, String type, String sujet, String description, Date date, String etat) {
        this.id = id;
        this.type = type;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }

    private int id;
    private String type;
    private int ido;
    private String sujet;
    private String description;
    private Date date;
    private String etat;
    private int User;

    public Reclamation(int id, String type, int ido, String sujet, String description, Date date, String etat, int User) {
        this.id = id;
        this.type = type;
        this.ido = ido;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.User = User;
    }

    public Reclamation(int id, String type, int ido, String sujet, String description, Date date, String etat) {
        this.id = id;
        this.type = type;
        this.ido = ido;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
    }

    public Reclamation(String type, String sujet, String description) {
        this.type = type;
        this.sujet = sujet;
        this.description = description;
    }

    public Reclamation(int id, String type, String sujet, String description) {
        this.id = id;

        this.type = type;
        this.sujet = sujet;
        this.description = description;
    }

    public Reclamation(String type, int ido, String sujet, String description, Date date, String etat, int User) {
        this.type = type;
        this.ido = ido;
        this.sujet = sujet;
        this.description = description;
        this.date = date;
        this.etat = etat;
        this.User = User;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", ido=" + ido + ", sujet=" + sujet + ", date=" + date + ", etat=" + etat + ", User=" + User + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int User) {
        this.User = User;
    }

}
