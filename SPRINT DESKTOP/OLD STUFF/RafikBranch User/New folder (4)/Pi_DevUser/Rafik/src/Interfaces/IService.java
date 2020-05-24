/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.util.List;

public interface IService<Utilisateurs> {
   public void add(Utilisateurs t);
   public void update(Utilisateurs t);
   public void remove(Utilisateurs t);
    public Utilisateurs findById(Utilisateurs r);
   public List<Utilisateurs> getAll();
    
}
