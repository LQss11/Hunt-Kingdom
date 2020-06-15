/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Entities.Utilisateurs;
import java.util.Optional;

/**
 *
 * @author RAFIK
 */
public class Session {
   private static Optional<Utilisateurs>  user;
   

    public static void start(Utilisateurs currentUser) {
        user.of(currentUser);
    }

    public static void close() throws Exception {
        if (user != null) {
            user = null;
        } else {
            throw new Exception("Session has not started yet!");
        }
    }
    public static Optional<Utilisateurs> getCurrentUser()
    {
     return user;   
    }

    
}
