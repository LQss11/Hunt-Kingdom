/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

/**
 *
 * @author LQss
 */
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import com.mycompany.utils.BCrypt;

/**
 *
 * @author bhk
 */
public class SignUpForm extends Form {
    //add/{username}/{email}/{password}/{firstname}/{lastname}/{phone}/{image}

    /*private void addImage(String imgPath) {
        ConnectionRequest connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog.show("Add Image", "Image added successfully", "OK", null);
            }
        };
        connectionRequest.setUrl("http://localhost/insert.php?path=" + imgPath);
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }*/

    public SignUpForm(Form previous) {

        setTitle("SIGN UP");

        setLayout(BoxLayout.y());

        TextField tfUsername = new TextField("", "Username: ");
        TextField tfEmail = new TextField("", "Email: ");
        TextField tfPassword = new TextField("", "Password: ");
        TextField tfFirstname = new TextField("", "Firstname: ");
        TextField tfLastname = new TextField("", "Lastname: ");
        TextField tfPhone = new TextField("", "Phone: ");
        TextField tfImage = new TextField("", "Image: ");
        
        tfPassword.setConstraint(TextField.PASSWORD);

        Button btnUploadImg = new Button("Add Image to DB");

      /*  btnUploadImg.addActionListener((ActionListener) (ActionEvent evt) -> {
            String imgPath = "D:/abc.";
            addImage(imgPath);
        });*/

        Button btnValider = new Button("Sign Up");

        btnValider.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {
                if ((tfUsername.getText().length() == 0) || (tfEmail.getText().length() == 0) || (tfPassword.getText().length() == 0) || (tfFirstname.getText().length() == 0) || (tfLastname.getText().length() == 0) || (tfPhone.getText().length() == 0) || (tfImage.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
                    try {
                        String hashed = BCrypt.hashpw(tfPassword.getText(), BCrypt.gensalt());

                        User u = new User(tfFirstname.getText(), tfLastname.getText(), tfEmail.getText(), hashed, tfPhone.getText(), tfImage.getText(), tfUsername.getText(), tfUsername.getText(), tfEmail.getText());
                        if (ServiceUser.getInstance().addUser(u)) {
                            Dialog.show("Success", "Hope you enjoy your journey in HuntKingdom", "OK", null);
                        } else {
                            Dialog.show("ERROR", "Server error", "OK", null);
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", "OK", null);
                    }

                }

            }
        }
        );

        add(tfUsername);
        add(tfEmail);
        add(tfPassword);
        add(tfFirstname);
        add(tfLastname);
        add(tfPhone);
        add(tfImage);
        add(btnValider);
        //add(btnUploadImg);

        getToolbar()
                .addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
