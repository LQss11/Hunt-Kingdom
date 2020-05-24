/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.EditProfilController.id;
import Entities.Evenement;
import Entities.Utilisateurs;
import Service.ServiceEvenement;
import Service.ServiceInscription;
import Util.DataSource;
import com.jfoenix.controls.JFXButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import static sun.security.krb5.Config.refresh;

/**
 * FXML Controller class
 *
 * @author aziz9
 */
public class ParticipationController implements Initializable {

    Label lbl;
    @FXML
    private VBox eventcontainer;
    @FXML
    private BarChart<String, Integer> chart;
    private JFXButton btn_logout;
    private JFXButton bnthome;
    private JFXButton ReclamationFront;
    private JFXButton EvntBtn;
    private JFXButton FeedbackFront;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadInscription();
    }

    public void loadInscription() {
        try {
            ServiceInscription pa = new ServiceInscription();
            ServiceEvenement se = new ServiceEvenement();
            List<VBox> list = new ArrayList<>();
            String req = "select * from evenement  ";
            Connection cnx = DataSource.getInstance().getCnx();
            PreparedStatement pre = cnx.prepareStatement(req);
            ResultSet rs = pre.executeQuery();

            while (rs.next()) {

                Evenement e1 = new Evenement(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getInt(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getString(10));
                Utilisateurs u = new Utilisateurs();

                Preferences user = Preferences.userRoot();
                int userId = user.getInt("UserId", id);
                u.setID(userId);

                Label nom = new Label("nom : " + e1.getNom());
                Label description = new Label("La description : " + e1.getDescription());
                Label date = new Label("Cet evenement est le : " + e1.getDate());

                HBox h1 = new HBox();
                h1.setSpacing(10);
                h1.setAlignment(Pos.CENTER);
                h1.getChildren().addAll(nom);
                HBox h2 = new HBox();
                h2.setSpacing(10);
                h2.setAlignment(Pos.CENTER);
                h2.getChildren().addAll(description);

                HBox h3 = new HBox();
                h3.setSpacing(10);
                h3.setAlignment(Pos.CENTER);
                h3.getChildren().addAll(date);
                Button bt1 = new Button("participer");
                Label bt2 = new Label("Deja Participer");
                Button bt3 = new Button("Annuler Participation");
                bt3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(".handle()");
                        try {
                            pa.deleteInscription(e1.getId(), u.getID());
                            eventcontainer.getChildren().clear();

                            loadInscription();

                        } catch (SQLException ex) {
                            Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                bt1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println(".handle()");
                        try {
                            pa.ajouterInscription(e1, u);
                            eventcontainer.getChildren().clear();
                            loadInscription();

                        } catch (SQLException ex) {
                            Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                String str = "file:" + System.getProperty("user.dir") + "/src/FXML/images/" + e1.getImage();
                //System.out.println(str);

                //ImageView iv = new ImageView(getClass().getResource(str).toExternalForm());
                ImageView iiv = new ImageView();

                iiv.setImage(new Image(str));
                iiv.setFitWidth(80);
                iiv.setFitHeight(80);

                VBox v = new VBox();
                v.setAlignment(Pos.CENTER);
                v.setSpacing(10);
                try {
                    if (TestParticipation(e1, u) == true) {
                        v.getChildren().add(iiv);
                        v.getChildren().addAll(h1, h2, h3, bt2, bt3);
                    } else {
                        v.getChildren().add(iiv);
                        v.getChildren().addAll(h1, h2, h3, bt1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                }
                VBox vv = new VBox();
                vv.setAlignment(Pos.CENTER);
                vv.setSpacing(10);

                HBox No = new HBox();
                No.setSpacing(10);
                No.setAlignment(Pos.CENTER);
                No.getChildren().addAll(vv, v);

                VBox v1 = new VBox();
                v1.setAlignment(Pos.CENTER);
                v1.setSpacing(10);
                v1.getChildren().addAll(No);
                list.add(v1);

            }
            eventcontainer.getChildren().addAll(list);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean TestParticipation(Evenement E, Utilisateurs U) throws SQLException {
        String req = "select * from inscription";
        Connection cnx = DataSource.getInstance().getCnx();
        PreparedStatement pre = cnx.prepareStatement(req);
        ResultSet rs = pre.executeQuery();
        while (rs.next()) {
            int e = E.getId();
            int u = U.getID();
            if ((rs.getInt(3) == u) && (rs.getInt(2) == e)) {
                return true;
            }
        }
        return false;
    }

    @FXML
    private void loadChart(ActionEvent event) {
        try {
            String query = "select nom,nbrplace FROM evenement ORDER BY   nbrplace";
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            Connection cnx = DataSource.getInstance().getCnx();
            ResultSet rss = null;
            try {
                rss = cnx.createStatement().executeQuery(query);
            } catch (SQLException ex) {
                Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (rss.next()) {
                try {
                    series.getData().add(new XYChart.Data<>(rss.getString(1), rss.getInt(2)));
                } catch (SQLException ex) {
                    Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            chart.getData().add(series);
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
