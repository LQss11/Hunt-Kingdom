/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author LQss
 */
public class Feedback {
    
    
    private int id ;
    private String description;
    private int rate ;
    private Date date;
    private int User;  

    public Feedback(int id, String description, int rate, Date date, int User) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.date = date;
        this.User = User;
    }

    public Feedback(String description, int rate, Date date, int User) {
        this.description = description;
        this.rate = rate;
        this.date = date;
        this.User = User;
    }

    public Feedback(int id, String description, int rate, Date date) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", description=" + description + ", rate=" + rate + ", date=" + date + ", User=" + User + '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int User) {
        this.User = User;
    }
    
}
