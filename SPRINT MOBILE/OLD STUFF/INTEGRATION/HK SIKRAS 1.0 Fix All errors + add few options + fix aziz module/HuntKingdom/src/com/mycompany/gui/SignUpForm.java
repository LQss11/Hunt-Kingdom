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
import com.codename1.capture.Capture;
import com.codename1.components.FileEncodedImage;
import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import com.mycompany.utils.BCrypt;
import com.mycompany.utils.Statics;

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
        TextField tfEmail = new TextField("", "E-Mail");
        TextField tfPassword = new TextField("", "Password: ");
        TextField tfFirstname = new TextField("", "Firstname: ");
        TextField tfLastname = new TextField("", "Lastname: ");
        TextField tfPhone = new TextField("", "Phone");
        Button btnImage = new Button("Image");

        tfPhone.setConstraint(TextArea.NUMERIC);

        tfPassword.setConstraint(TextField.PASSWORD);

        Validator valEmail = new Validator();
        valEmail.addConstraint(tfEmail, RegexConstraint.validEmail());

        Validator valPhone = new Validator();
        RegexConstraint phoneConstraint = new RegexConstraint("^[0-9]{8}$", "Invalid phone num");
        valPhone.addConstraint(tfPhone, phoneConstraint);

        Validator valName = new Validator();
        RegexConstraint nameConstraint = new RegexConstraint("[A-Za-z]$", "Invalid name");
        valName.addConstraint(tfFirstname, nameConstraint);
        valName.addConstraint(tfUsername, nameConstraint);
        valName.addConstraint(tfLastname, nameConstraint);

        Button btnUploadImg = new Button("Add Image to DB");

        Button btnValider = new Button("Sign Up");

        btnValider.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {

                if (!valEmail.isValid()) {
                    ToastBar.showErrorMessage("Wrong email form");
                } else if (!valPhone.isValid()) {
                    ToastBar.showErrorMessage("Wrong Phone Numnber");
                } else if (!valName.isValid()) {
                    ToastBar.showErrorMessage("Wrong Name no digits allowed");
                } else if ((tfUsername.getText().length() == 0) || (tfEmail.getText().length() == 0) || (tfPassword.getText().length() == 0) || (tfFirstname.getText().length() == 0) || (tfLastname.getText().length() == 0) || (tfPhone.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                    System.out.println(".actionPerformed()" + valPhone.isValid());
                } else {
                    try {
                        String hashed = BCrypt.hashpw(tfPassword.getText(), BCrypt.gensalt());
                        String picture = Capture.capturePhoto(1024, -1);
                        FileEncodedImage setimg = FileEncodedImage.create(picture, 120, 120);

                        User u = new User(tfFirstname.getText(), tfLastname.getText(), tfEmail.getText(), hashed, tfPhone.getText(), "aaaaa", tfUsername.getText(), tfUsername.getText(), tfEmail.getText());
                        if (ServiceUser.getInstance().addUser(u, setimg.getImageData())) {
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

        //add(btnImage);

        add(btnValider);
        //add(btnUploadImg);

        getToolbar()
                .addMaterialCommandToLeftBar(previous.getTitle(), FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack()); // Revenir vers l'interface précédente

    }

}
