/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Controllers.User_HomeController;
import Entities.UtilisateursAdresses;
import Service.ServiceUtilisateursAdresses;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import Entities.Produit;
import hunt.Hunt;
import Entities.Commandes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import Entities.Commandes;
import Entities.Tva;
import Service.ServiceCommandes;
import com.jfoenix.controls.JFXButton;

import static hunt.Hunt.listeprod;
import java.nio.charset.Charset;
import static java.rmi.Naming.list;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import static java.util.Collections.list;
import java.util.Random;
import java.util.prefs.Preferences;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class AdresselivraisonController implements Initializable {

    @FXML
    private JFXListView<String> lv;
    @FXML
    private JFXTextField state;
    @FXML
    private JFXTextField city;
    @FXML
    private JFXTextField zip;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField line1;

    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField complement;

    private List<UtilisateursAdresses> lad;

    private ServiceUtilisateursAdresses sral;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton buttonPayer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sral = new ServiceUtilisateursAdresses();

        setData();
    }

    public void setData() {
        Preferences user = Preferences.userRoot();
        int userId = user.getInt("UserId", 0);

        try {

            lad = sral.sortedbyId(userId);
        } catch (SQLException ex) {
            Logger.getLogger(AdresselivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lv.getItems().clear();
        lad.forEach((t) -> {
            String str = "Adresse : \n"
                    + t.getPays()
                    + " \n" + t.getVille()
                    + " \n" + t.getCp()
                    + " \n" + t.getTelephone()
                    + " \n" + t.getAdresse()
                    + " \n" + t.getPrenom()
                    + " \n" + t.getNom()
                    + " \n" + t.getComplement();

            lv.getItems().add(str);
        });
    }

    @FXML
    private void Ajouteradr(ActionEvent event) throws SQLException {

        Preferences user = Preferences.userRoot();
        int userId = user.getInt("UserId", 0);

        if (state.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("region est Obligatoire");
            alert.showAndWait();
            return;
        }

        if (city.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("ville est Obligatoire");
            alert.showAndWait();
            return;
        }

        if (zip.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("code postal est Obligatoire");
            alert.showAndWait();
            return;
        }

        if (tel.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Adresse line 1 est Obligatoire");
            alert.showAndWait();
            return;
        }
        if (line1.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Adresse line 1 est Obligatoire");
            alert.showAndWait();
            return;
        }
        if (nom.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Adresse line 1 est Obligatoire");
            alert.showAndWait();
            return;
        }
        if (prenom.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Adresse line 1 est Obligatoire");
            alert.showAndWait();
            return;
        }
        if (complement.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Adresse line 1 est Obligatoire");
            alert.showAndWait();
            return;
        }

        UtilisateursAdresses r = new UtilisateursAdresses(nom.getText(), prenom.getText(), tel.getText(), line1.getText(), zip.getText(), state.getText(), city.getText(), complement.getText());
        if (sral.ajouterUtilisateursAdresses(r, userId) > 0) {
            setData();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText("Adresse est ajouté");
        alert.showAndWait();

    }

    @FXML
    private void suprimeradresse(ActionEvent event) throws SQLException {

        int index = lv.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            if (sral.deleteUtilisateursAdresses(lad.get(index).getId()) > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alert");
                alert.setHeaderText("Adresse suprimmer");
                alert.show();
                setData();

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText("Vous devez choisir une adresse");
            alert.show();
        }

    }

    @FXML
    private void payer(ActionEvent event) throws SQLException {

        if (lv.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("vous devez selectionez une adresse ");
            alert.showAndWait();
        } else {
            // checkout(lad.get(lv.getSelectionModel().getSelectedIndex()));
            PasserCMD(lad.get(lv.getSelectionModel().getSelectedIndex()));

        }
    }

    public String decrypttva(ArrayList<Produit> produits, UtilisateursAdresses utilisateur) {
        String str = "";
        String strProduit = "";
        int TotaleHT = 0;
        int TotaleTTC = 0;

        for (int i = 0; i < produits.size(); i++) {
            int k = i + 1;
            String s = "i:" + k
                    + ";a:5:{s:2:\"id\";i:" + produits.get(i).getId()
                    + ";s:9:\"reference\";s:" + produits.get(i).getNom().length()
                    + ":\"" + produits.get(i).getNom()
                    + "\";s:8:\"quantite\";s:1:\"" + produits.size()
                    + "\";s:6:\"prixHT\";d:" + Math.round(produits.get(i).getPrix())
                    + ";s:7:\"prixTTC\";d:" + Math.round(produits.get(i).getPrix())
                    + ";}";
            strProduit += s;
        }

        for (int i = 0; i < produits.size(); i++) {
            int Tot = Math.round(produits.get(i).getPrix());
            TotaleHT += Tot;
        }

        for (int i = 0; i < produits.size(); i++) {
            int Tot1 = Math.round(produits.get(i).getPrix());
            TotaleTTC += Tot1;
        }

        str += "a:7:{s:3:\"tva\";a:1:{s:2:\"%1\";d:" + 0
                + ";}"
                + "s:7:\"produit\";a:" + produits.size() + ":{" + strProduit
                + "}s:9:\"livraison\";a:8:{s:6:\"prenom\";s:" + utilisateur.getPrenom().length()
                + ":\"" + utilisateur.getPrenom()
                + "\";s:3:\"nom\";s:" + utilisateur.getNom().length()
                + ":\"" + utilisateur.getNom()
                + "\";s:9:\"telephone\";s:" + utilisateur.getTelephone().length()
                + ":\"" + utilisateur.getTelephone()
                + "\";s:7:\"adresse\";s:" + utilisateur.getAdresse().length()
                + ":\"" + utilisateur.getAdresse()
                + "\";s:2:\"cp\";s:" + utilisateur.getCp().length()
                + ":\"" + utilisateur.getCp()
                + "\";s:5:\"ville\";s:" + utilisateur.getVille().length()
                + ":\"" + utilisateur.getVille()
                + "\";s:4:\"pays\";s:" + utilisateur.getPays().length()
                + ":\"" + utilisateur.getPays()
                + "\";s:10:\"complement\";s:" + utilisateur.getComplement().length()
                + ":\"" + utilisateur.getComplement()
                + "\";}s:11:\"facturation\";a:8:{s:6:\"prenom\";s:" + utilisateur.getPrenom().length()
                + ":\"" + utilisateur.getPrenom()
                + "\";s:3:\"nom\";s:" + utilisateur.getNom().length()
                + ":\"" + utilisateur.getNom()
                + "\";s:9:\"telephone\";s:" + utilisateur.getTelephone().length()
                + ":\"" + utilisateur.getTelephone()
                + "\";s:7:\"adresse\";s:" + utilisateur.getAdresse().length()
                + ":\"" + utilisateur.getAdresse()
                + "\";s:2:\"cp\";s:" + utilisateur.getCp().length()
                + ":\"" + utilisateur.getCp()
                + "\";s:5:\"ville\";s:" + utilisateur.getVille().length()
                + ":\"" + utilisateur.getVille()
                + "\";s:4:\"pays\";s:" + utilisateur.getPays().length()
                + ":\"" + utilisateur.getPays()
                + "\";s:10:\"complement\";s:" + utilisateur.getComplement().length()
                + ":\"" + utilisateur.getComplement()
                + "\";}s:6:\"prixHT\";d:" + TotaleHT
                + ";s:7:\"prixTTC\";d:" + TotaleTTC
                + ";s:5:\"token\";"
                + "s:40:\"58f995582826e0e74169d0d33010fdbf468724b2\";"
                + "}";
        return str;
    }

    /*
    public void checkout(UtilisateursAdresses adresse) {

        ServiceCommandes service = new ServiceCommandes();
        UtilisateursAdresses ua = new UtilisateursAdresses("mallek", "skander", "21767868", "qdsqds", "400", "tunis", "sousse", "ki zebii");

        Produit p2 = new Produit("hrisa", 100, "3asba", 1, "qdsdsfdsfdsf");
        Produit p1 = new Produit("sicam", 100, "dsfdsf", 1, "dsgfdsgdf");

        List l = new ArrayList<Object>();

        l.add(p1);

        Commandes c1 = new Commandes(1, 2, true, Date.valueOf(LocalDate.now()), 5, "");

        String list = decrypttva((ArrayList<Produit>) listeprod, adresse);

        System.out.println(list);

    }*/

    public void PasserCMD(UtilisateursAdresses adresse) throws SQLException {
        Random rand = new Random();
        int rand_int1 = rand.nextInt(1000);

        Preferences user = Preferences.userRoot();
        int userId = user.getInt("UserId", 0);

        ServiceCommandes scm = new ServiceCommandes();

        Commandes cmd = new Commandes();

        cmd.setUtilisateur_id(userId);

        cmd.setDate(Date.valueOf(LocalDate.now()));

        cmd.setReference(rand_int1);

        cmd.setValider(Boolean.valueOf("0"));

        String List = decrypttva((ArrayList<Produit>) listeprod, adresse);

        cmd.setCommande(List);
        scm.ajouterCommande(cmd);
        System.out.println(List);
        System.out.println("commadne t3addet");

        try {
            Util.Checkout.execPaymant(cmd);
        } catch (IOException ex) {
            Logger.getLogger(AdresselivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (URISyntaxException ex) {
            Logger.getLogger(AdresselivraisonController.class.getName()).log(Level.SEVERE, null, ex);
        }

        hunt.Hunt.listeprod.clear();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Merci");
        alert.showAndWait();
        buttonPayer.getScene().getWindow().hide();

    }

    private void store(ActionEvent event) {
        Parent root;
        try {
            Stage stage = new Stage();
            root = (ScrollPane) FXMLLoader.load(getClass().getResource("/FXML/User_Home.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
