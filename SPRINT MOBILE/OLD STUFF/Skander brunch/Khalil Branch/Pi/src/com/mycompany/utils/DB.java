/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.codename1.db.Database;
import java.io.IOException;

/**
 *
 * @author skand
 */
public class DB {  
    private static Database db;

    private DB() {
        try {
            Boolean created = Database.exists("base.db");
            db = Database.openOrCreate("base.db");

            if (created == false) {
                db.execute("create table panier (id INTEGER PRIMARY KEY ,titre TEXT,prix DOUBLE ,image TEXT);");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static Database getInstance()
    {   
        if(db==null)
        {
            new DB();
        }
        return db;
    }
    
}
