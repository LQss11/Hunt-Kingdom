/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


/**
 *
 * @author LQss & Rafik
 */
public class User {

    private int id, enabled;
    private String firstname, lastname, email, password, phone, image, role, username, username_canonical, email_canonical, date, last_login;
    public int idSession;
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", enabled=" + enabled + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", password=" + password + ", phone=" + phone + ", image=" + image + ", role=" + role + ", username=" + username + ", username_canonical=" + username_canonical + ", email_canonical=" + email_canonical + ", date=" + date + ", last_login=" + last_login + '}';
    }

    public User(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLast_login() {
        return last_login;
    }

    public void setLast_login(String last_login) {
        this.last_login = last_login;
    }

    public User(String firstname, String lastname, String email, String password, String phone, String image, String username, String username_canonical, String email_canonical) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.image = image;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email_canonical = email_canonical;
    }


    public User(int id, int enabled, String firstname, String lastname, String email, String password, String phone, String image, String role, String username, String username_canonical, String email_canonical, String date, String last_login) {
        this.id = id;
        this.enabled = enabled;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.image = image;
        this.role = role;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email_canonical = email_canonical;
        this.date = date;
        this.last_login = last_login;
    }

    public User() {
    }

}
