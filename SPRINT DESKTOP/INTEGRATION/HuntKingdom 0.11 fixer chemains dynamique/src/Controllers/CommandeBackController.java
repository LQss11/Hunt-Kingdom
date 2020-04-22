/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Commandes;
import Entities.Feedback;
import Entities.ProduitPayement;
import Util.DataBase;
import de.ailis.pherialize.MixedArray;
import de.ailis.pherialize.Pherialize;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import static junit.framework.Assert.assertEquals;

/**
 * FXML Controller class
 *
 * @author skand
 */
public class CommandeBackController implements Initializable {

    private ObservableList<Commandes> dataCommandes;
    private ObservableList<ProduitPayement> dataProduits;
    private Connection con;
    private ResultSet rs = null;
    private PreparedStatement pst;
    @FXML
    private TableView<ProduitPayement> TableViewProduits;
    @FXML
    private Button buttonAfficher;
    @FXML
    private Label totalPrix;
    @FXML
    private TableColumn<?, ?> nbrprod;

    public void refresh() {
        loadCommandesBack();
        afficherCommandeBack();
    }

    @FXML
    private TableView<Commandes> tableviewcommande;
    @FXML
    private TableColumn<Commandes, ?> userid;
    @FXML
    private TableColumn<Commandes, ?> date;
    private TableColumn<Commandes, ?> cmd;

    @FXML
    private TableColumn<ProduitPayement, ?> nomprod;
    @FXML
    private TableColumn<ProduitPayement, ?> qnt;
    @FXML
    private TableColumn<ProduitPayement, ?> prixunitaire;
    @FXML
    private TableColumn<ProduitPayement, ?> totaleprod;
    @FXML
    private TableColumn<Commandes, ?> valide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        con = DataBase.getInstance().getConnection();
        dataCommandes = FXCollections.observableArrayList();
        refresh();
    }

    public Commandes gettempCommande(TableColumn.CellEditEvent edittedCell) {
        Commandes test = tableviewcommande.getSelectionModel().getSelectedItem();
        return test;
    }

    private void loadCommandesBack() {

        dataCommandes.clear();

        try {

            pst = con.prepareStatement("Select * from commandes");

            rs = pst.executeQuery();
            while (rs.next()) {

                //nom produit quantite prix unitaure totale totale
                // il faut que les nom sont les memes dans la base des donnees
                dataCommandes.add(new Commandes(rs.getInt("utilisateur_id"), rs.getBoolean("valider"), rs.getDate("date"),rs.getString("commande")));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        tableviewcommande.setItems(dataCommandes);

    }

    private void afficherCommandeBack() {
        userid.setCellValueFactory(new PropertyValueFactory<>("utilisateur_id"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        valide.setCellValueFactory(new PropertyValueFactory<>("valider"));
        nbrprod.setCellValueFactory(new PropertyValueFactory<>("commande"));

    }

    @FXML
    private void showCommande(ActionEvent event) throws SQLException {

        int i;
        TableColumn.CellEditEvent edittedcell = null;
        Commandes x = gettempCommande(edittedcell);
        String commande = x.getCommande();
        MixedArray list = Pherialize.unserialize(commande).toArray();
        MixedArray produits = list.getArray("produit");

        //System.out.println("list = " + produits.keySet().toArray());
        nomprod.setCellValueFactory(new PropertyValueFactory<>("reference"));
        qnt.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        prixunitaire.setCellValueFactory(new PropertyValueFactory<>("prixUni"));
        totaleprod.setCellValueFactory(new PropertyValueFactory<>("prixTotal"));

        ObservableList<ProduitPayement> prods = FXCollections.observableArrayList();

        for (Object key : produits.keySet()) {

            ProduitPayement prod = new ProduitPayement(String.valueOf(produits.getArray(key).getString("reference")), produits.getArray(key).getInt("quantite"), produits.getArray(key).getDouble("prixTTC"), produits.getArray(key).getDouble("prixTTC") * produits.getArray(key).getInt("quantite"));
            prods.add(prod);
        }

        TableViewProduits.setItems(prods);
        totalPrix.setText(String.valueOf(list.getDouble("prixTTC")));

    }

}
