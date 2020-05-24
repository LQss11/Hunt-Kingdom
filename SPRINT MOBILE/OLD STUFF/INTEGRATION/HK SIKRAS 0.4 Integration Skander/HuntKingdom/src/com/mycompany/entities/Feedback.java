/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author LQss
 */
public class Feedback {
    private int id ;
    private String description;
    private int rate ;
    private String date;

    @Override
    public String toString() {
        return "Feedback{" + "id=" + id + ", description=" + description + ", rate=" + rate + ", date=" + date + ", User=" + User + '}';
    }

    public Feedback(int id) {
        this.id = id;
    }

    public Feedback(String description, int rate) {
        this.description = description;
        this.rate = rate;
    }

    public Feedback(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public Feedback(String description, int rate, int User) {
        this.description = description;
        this.rate = rate;
        this.User = User;
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

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int User) {
        this.User = User;
    }

    public Feedback(String description, int rate, String date, int User) {
        this.description = description;
        this.rate = rate;
        this.date = date;
        this.User = User;
    }

    public Feedback(int id, String description, int rate, String date, int User) {
        this.id = id;
        this.description = description;
        this.rate = rate;
        this.date = date;
        this.User = User;
    }

    public Feedback() {
    }
    private int User;  
}
