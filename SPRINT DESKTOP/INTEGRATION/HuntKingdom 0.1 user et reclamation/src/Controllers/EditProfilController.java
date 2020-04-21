/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import static Controllers.SignInController.id;
import Entities.Utilisateurs;
import Service.UtilisateursService;
import Util.BCrypt;
import Util.DataSource;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author RAFIK
 */
public class EditProfilController implements Initializable {

    @FXML
    private AnchorPane signupPane;
    @FXML
    private JFXTextField txtfirstname;
    @FXML
    private JFXPasswordField txtpasswod;
    @FXML
    private JFXButton btnupdate;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private Label lblstatus;
    @FXML
    private Label lblemail;
    @FXML
    private JFXButton imgechoser;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXDatePicker date;
    @FXML
    private JFXTextField txtPhone;
    String getImageUrl, mail;
    @FXML
    private ImageView picId;
    public static int id;
    private Connection con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = DataSource.getInstance().getCnx();
    }

    void setUserInfo(String mail) {

        if (mail == null) {
            return;
        }
        UtilisateursService u1 = new UtilisateursService();
        Utilisateurs user = u1.findByMail(mail);
        txtlastname.setText(user.getFIRST_NAME());
        txtfirstname.setText(user.getLAST_NAME());

        lblemail.setText(mail);
        getImageUrl = user.getIMAGE();
        File file = new File(getImageUrl);
        Image ima = new Image(file.toURI().toString());
        //imgechoser.setImage(ima);     
    }

    @FXML
    private void UpdateBtn(ActionEvent event) throws ParseException, SQLException {

        int x = 0;

        Preferences UserId = Preferences.userRoot();
        int userId = UserId.getInt("UserId", id);

        System.out.println("Controller id est " + UserId.getInt("UserId", 1));
        System.out.println("Controller id est " + UserId.getInt("UserId", userId));

        UtilisateursService u1 = new UtilisateursService();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
        Date date1 = new Date(System.currentTimeMillis());
        String hashed2 = BCrypt.hashpw(txtpasswod.getText(), BCrypt.gensalt());
        Utilisateurs user = new Utilisateurs(userId, txtfirstname.getText(), txtlastname.getText(), txtEmail.getText(), hashed2, txtPhone.getText(), imgechoser.getText(), date1);

        int i;

        i = u1.updateUser(user);

        if (txtfirstname != null) {
            user.setFIRST_NAME(txtfirstname.getText());
        }
        if (txtlastname != null) {
            user.setLAST_NAME(txtlastname.getText());
        }
        if (txtpasswod != null) {
            String hashed = BCrypt.hashpw(txtpasswod.getText(), BCrypt.gensalt());
            user.setPASSWORD(hashed);
        }

        if (txtPhone != null) {
            user.setPHONE(txtPhone.getText());
        }

        /*
        user.setIMAGE(getImageUrl);   
        u1.update(user);
        setUserInfo(mail);
         */
        if (i == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText(" updated");
            alert.showAndWait();

        }
    }

    @FXML
    private void changephoto(MouseEvent event) {

        FileChooser fc = new FileChooser();

        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fc.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File selectedFile = fc.showOpenDialog(null);
        try {
            BufferedImage bufferedImage = ImageIO.read(selectedFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            picId.setImage(image);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
