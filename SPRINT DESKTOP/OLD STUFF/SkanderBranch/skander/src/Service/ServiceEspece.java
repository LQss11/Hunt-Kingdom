/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Util.DataSource;
import Entities.Espece;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ilyess
 */
public class ServiceEspece {
     private Connection con;
    private Statement ste;
    private PreparedStatement pst;
    public ServiceEspece(){
       con = DataSource.getInstance().getCnx();
    }
       public static void ajouterEspece(Espece p) throws SQLException {
             Connection cnx = DataSource.getInstance().getCnx();
         Statement st = cnx.createStatement();

   try { 
            String req = "INSERT INTO `espece`( `nomEspece`, `descriptionEspece`, `image`, `poids`, `type`, `zone`, `ville`, `idS`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement pre = cnx.prepareStatement(req);
                      
            pre.setString(1,p.getNomEspece()) ; 
            pre.setString(2,p.getDescriptionEspece()) ;
            pre.setString(3,p.getImage()) ;
            
            pre.setString(4,p.getPoids()) ;           
            pre.setString(5,p.getType()) ;
            pre.setString(6,p.getZone()) ;
            pre.setString(7,p.getVille()) ;
            pre.setInt(8,p.getIdS()) ;
            pre.executeUpdate() ; 
            System.out.println("DONE Espece");
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
   
    }
       
        public void modifierEspece(Espece e) throws SQLException {
        
        String requetemodifier = "UPDATE `espece` SET `nomEspece`=?, `descriptionEspece`=?, "
                   + "`image`=? ,`poids`=?,`type`=?,`zone`=?, `ville`=?,`idS`=? WHERE `idEspece`=?";  
        PreparedStatement pre = con.prepareStatement(requetemodifier);
           
        pre.setString(1,e.getNomEspece());
        pre.setString(2,e.getDescriptionEspece());
        pre.setString(3,e.getImage());
        pre.setString(4,e.getPoids());
        pre.setString(5,e.getType());
        pre.setString(6,e.getZone());
        pre.setString(7,e.getVille());        
        pre.setInt(8,e.getIdS());
        pre.setInt(9,e.getIdEspece());
       
        pre.executeUpdate();
    }
        
        public int deleteEspece(int idEspece) throws SQLException {
        int i = 0;
        try {
            ste = con.createStatement();
            String sql = "DELETE FROM `Espece` WHERE idEspece=" + idEspece;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEspece.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ste.close();
        }
        return i;
    }
        public List<Espece> readAll() throws SQLException {
       List<Espece> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet result = ste.executeQuery("select * from `espece`");
        while (result.next()){
            int idEspece=result.getInt(1);
            String nomEspece=result.getString(2);
            String descriptionEspece=result.getString(3);
            String image=result.getString(4);
            String poids=result.getString(4);
            String type=result.getString(5);
            String zone=result.getString(6);
            String ville=result.getString(7);
            int idS=result.getInt(9);
            Espece e= new Espece(idEspece,nomEspece,descriptionEspece,image,poids,type,zone,ville,idS);
            arr.add(e);        
        }
       return arr;
    }
         public List<Espece> readAllImage() throws SQLException {
       List<Espece> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet result = ste.executeQuery("select image from `espece`");
        while (result.next()){
           
            String image=result.getString(4);
           
            Espece e= new Espece(image);
            arr.add(e);        
        }
       return arr;
    }
         public int idEspeceFromNom(String nom) throws SQLException {
       int i=0;
        ste = con.createStatement();
        ResultSet result = ste.executeQuery("select * from `espece` where `nomEspece`='"+nom+"'");
        while (result.next()){
            System.out.println("d5al hne");
            int idEspece=result.getInt("idEspece");
           return idEspece;
        }
             System.out.println("select * from `espece` where `nomEspece`='"+nom+"'");
       return i;
    }
        
        
        
        
        public List<Espece> sortedbyType(String type) throws SQLException {
        List<Espece> arr = new ArrayList<>();
        ste = con.createStatement();
          ResultSet result=ste.executeQuery("select * from espece where nomEspece='"+type+"'");
        while (result.next()) {
             int idEspece=result.getInt(1);
            String nomEspece=result.getString(2);
            String descriptionEspece=result.getString(3);
            String image=result.getString(4);
            String poids=result.getString(5);
            String zone=result.getString(6);
            String ville=result.getString(7);
            int idS=result.getInt(9);
            Espece e= new Espece(idEspece,nomEspece,descriptionEspece,image,poids,type,zone,ville,idS);
            
      
            arr.add(e);
        }
        return arr;
    }
         public List<Espece> sortedbyNom(String nomEspece) throws SQLException {
        List<Espece> arr = new ArrayList<>();
        ste = con.createStatement();
          ResultSet result=ste.executeQuery("select * from espece where nomEspece='"+nomEspece+"'");
        while (result.next()) {
             int idEspece=result.getInt(1);
            //String nomEspece=result.getString(2);
            String descriptionEspece=result.getString(3);
            String image=result.getString(4);
            String poids=result.getString(5);
            String type=result.getString(6);
            String zone=result.getString(7);
            String ville=result.getString(8);
            int idS=result.getInt(9);
            Espece e= new Espece(idEspece,nomEspece,descriptionEspece,image,poids,type,zone,ville,idS);
            
        

            System.out.println("jbed we7ed");
            arr.add(e);
        }
        return arr;
         }
      public List<Espece> sortedbyS(int idS) throws SQLException {
        List<Espece> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet result=ste.executeQuery("select * from espece where idS='"+idS+"'");
        while (result.next()) {
             int idEspece=result.getInt(1);
            String nomEspece=result.getString(2);
            String descriptionEspece=result.getString(3);
            String image=result.getString(4);
            String poids=result.getString(5);
            String type=result.getString(6);
            String zone=result.getString(7);
            String ville=result.getString(8);
            //int idS=result.getInt(9);
            Espece e= new Espece(idEspece,nomEspece,descriptionEspece,image,poids,type,zone,ville,idS);
            
        

            System.out.println("jbed we7ed");
            arr.add(e);
        }
        return arr;
    }
}
