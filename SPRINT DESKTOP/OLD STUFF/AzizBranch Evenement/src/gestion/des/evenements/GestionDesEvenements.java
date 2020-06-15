/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion.des.evenements;
import Utils.DataSource;
import java.sql.Connection;
import Entities.Evenement;
import Entities.User;
import java.sql.SQLException;
import Service.ServiceEvenement;
import Service.ServiceInscription;
/**
 *
 * @author aziz9
 */
public class GestionDesEvenements {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        // TODO code application logic here
        DataSource ds = DataSource.getInstance();
        Connection cnx = DataSource.getInstance().getCnx();
        Evenement E = new Evenement(2,"testfff", "test" ,1.5 , 5 ,"test", "test", 5, "test");
        User U=new User(3);
        ServiceEvenement sp= new ServiceEvenement();
        ServiceInscription si=new ServiceInscription();
                try {
                    //sp.afficherEvenement();
                    //sp.ajouterEvenement(E);
                    //sp.deleteProduit(1);
                    //sp.ModifierEvenement(E);
                    si.deleteInscription(3, 3);
                  //si.ajouterInscription(E,U);
                }catch (SQLException ex) {
            System.out.println(ex);        }    
        
    }
    
}
