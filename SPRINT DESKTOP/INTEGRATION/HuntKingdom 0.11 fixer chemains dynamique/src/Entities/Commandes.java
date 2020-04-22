/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author skand
 */
public class Commandes {
    private int id;
    private int utilisateur_id;
    private boolean valider;
    private Date  date;
    private int reference;
    private String commande;

    public Commandes(int id, int utilisateur_id, boolean valider, Date date, int reference, String commande) {
        this.id = id;
        this.utilisateur_id = utilisateur_id;
        this.valider = valider;
        this.date = date;
        this.reference = reference;
        this.commande = commande;
    }

    public Commandes(int utilisateur_id, boolean valider, Date date, String commande) {
        this.utilisateur_id = utilisateur_id;
        this.valider = valider;
        this.date = date;
        this.commande = commande;
    }

    public Commandes(int utilisateur_id, Date date, String commande) {
        this.utilisateur_id = utilisateur_id;
        this.date = date;
        this.commande = commande;
    }

    public Commandes(int utilisateur_id, boolean valider, Date date) {
        this.utilisateur_id = utilisateur_id;
        this.valider = valider;
        this.date = date;
    }
    

    
    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
    }

    public Commandes() {
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

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getReference() {
        return reference;
    }

    public void setReference(int reference) {
        this.reference = reference;
    }

    public Commandes(int utilisateur_id, boolean valider, Date date, int reference, String commande) {
        this.utilisateur_id = utilisateur_id;
        this.valider = valider;
        this.date = date;
        this.reference = reference;
        this.commande = commande;
    }

    @Override
    public String toString() {
        return "Commandes{" + "id=" + id + ", utilisateur_id=" + utilisateur_id + ", valider=" + valider + ", date=" + date + ", reference=" + reference + ", commande=" + commande + '}';
    }

    public void setUtilisateur_id(Utilisateurs userFront) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
