/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;
import Entities.Evenement;
import java.util.List;


/**
 *
 * @author aziz9
 */
    public interface IServiceEvenement {
    public void AjouterEvenement(Evenement E);
    public void ModifierEvenement(Evenement E);
    public void SupprimerEvenement(Evenement E);
    public List<Evenement> AfficherEvenement ();
    
}
