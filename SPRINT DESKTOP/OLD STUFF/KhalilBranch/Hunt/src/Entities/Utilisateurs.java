/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author RAFIK
 */
public class Utilisateurs{
    
      
    private int ID,STATUS;
    private String FIRST_NAME,LAST_NAME,EMAIL,PASSWORD,PHONE,IMAGE;
    private Date date;
   
    public Utilisateurs(){};

    public Utilisateurs(int ID, int STATUS, String PHONE, String FIRST_NAME, String LAST_NAME, String EMAIL,String IMAGE, String PASSWORD, Date date) {
        this.ID = ID;
        this.STATUS = STATUS;
        this.PHONE = PHONE;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.date = date;
        this.IMAGE = IMAGE;
    }
    
    

    public Utilisateurs(String EMAIL, String PASSWORD) {
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
    }

    public Utilisateurs(String FIRST_NAME, String EMAIL, String PASSWORD, Date date,String LAST_NAME,String PHONE,String IMAGE) {
        this.PHONE = PHONE;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.date = date;
        this.IMAGE = IMAGE;
    }

    
    public Utilisateurs(String FIRST_NAME, String EMAIL, String PASSWORD, Date date,String LAST_NAME,String PHONE,String IMAGE,int STATUS) {
        this.PHONE = PHONE;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
        this.PASSWORD = PASSWORD;
        this.date = date;
        this.IMAGE = IMAGE;
        this.STATUS=STATUS;}

    public Utilisateurs(int ID, int STATUS, String FIRST_NAME, String LAST_NAME, String EMAIL) {
        this.ID = ID;
        this.STATUS = STATUS;
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL = EMAIL;
    }
    
    
    
    
    
    public String getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(String IMAGE) {
        this.IMAGE = IMAGE;
    }

    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.FIRST_NAME);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Utilisateurs other = (Utilisateurs) obj;
        if (!Objects.equals(this.FIRST_NAME, other.FIRST_NAME)) {
            return false;
        }
        if (!Objects.equals(this.LAST_NAME, other.LAST_NAME)) {
            return false;
        }
        if (!Objects.equals(this.EMAIL, other.EMAIL)) {
            return false;
        }
        if (!Objects.equals(this.PASSWORD, other.PASSWORD)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Utilisateurs{" + "ID=" + ID + ", STATUS=" + STATUS + ", PHONE=" + PHONE + ", FIRST_NAME=" + FIRST_NAME + ", LAST_NAME=" + LAST_NAME + ", EMAIL=" + EMAIL + ", PASSWORD=" + PASSWORD + ", date=" + date + '}';
    }
    
    
    
    
    
    
    
}
