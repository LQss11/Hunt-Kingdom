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
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.Preferences;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.geom.Dimension;
import com.mycompany.entities.User;
import com.mycompany.services.ServiceUser;
import com.mycompany.utils.BCrypt;
import java.io.IOException;
import java.util.ArrayList;
import com.codename1.components.ToastBar;
import com.codename1.ui.util.UITimer;



public class LoginForm extends Form {

    Form current;
    EncodedImage enc;
    public static User US;

    public LoginForm() {

        current = this;
        setTitle("Login");

        Preferences UserId = null;
        UserId.delete("UserId");

        setLayout(BoxLayout.y());

        ScaleImageLabel myPic = new ScaleImageLabel();
        Image img = null;
        try {
            img = Image.createImage("/hk_logo.png");
        } catch (IOException ex) {
            System.out.println("Image logo does not exist");
        }
        myPic.setIcon(img);
        Dimension d = new Dimension(50, 50);
        myPic.setPreferredSize(d);

        TextField tfUsername = new TextField("", "Username:");
        TextField tfPassword = new TextField("", "Password: ");
        tfPassword.setConstraint(TextField.PASSWORD);
        Button btnSignIn = new Button("Sign In");
        Button btnSignUp = new Button("Sign Up");

        btnSignUp.addActionListener(e
                -> new SignUpForm(current).show());

        /*
        Button btnSignInFaceBook = new Button("FACEBOOK");

        btnSignInFaceBook.addActionListener((e) -> {
            //use your own facebook app identifiers here   
            //These are used for the Oauth2 web login process on the Simulator.
            String clientId = "2214089125566786";
            String redirectURI = "https://localhost";
            String clientSecret = "ba48e313a0734fca5d0a4f1628b16601";
            //use your own facebook app identifiers here   
            //These are used for the Oauth2 web login process on the Simulator.

            Login fb = FacebookConnect.getInstance();
            fb.setClientId(clientId);
            fb.setRedirectURI(redirectURI);
            fb.setClientSecret(clientSecret);
            //Sets a LoginCallback listener
            //trigger the login if not already logged in
            fb.setCallback(new LoginCallback() {
                @Override
                public void loginFailed(String errorMessage) {
                    System.out.println(" faileed login");
                    Storage.getInstance().writeObject("token", "");
                    //showIfNotLoggedIn(form);
                }

                @Override
                public void loginSuccessful() {
                    System.out.println("Succ login");
                    String token = fb.getAccessToken().getToken();
                    Storage.getInstance().writeObject("token", token);
                    System.out.println("token ==" + token);

                    //showIfLoggedIn(form);
                }

            });
            if (!fb.isUserLoggedIn()) {
                fb.doLogin();
                System.out.println("not logged in " + fb);
//                System.out.println("logged logged in " + fb.getAccessToken().getObjectId());
                // fb.setClientId("12121");

            } else {
                //get the token and now you can query the facebook API
                String token = fb.getAccessToken().getToken();
                System.out.println("logged logged in " + fb.getAccessToken().getObjectId());

                System.out.println("logged in " + token);

            }
        });
         */
        btnSignIn.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {
				

                if ((tfUsername.getText().length() == 0) || (tfPassword.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", "OK", null);
                } else {
					
					
                    try {
						                ToastBar.Status status = ToastBar.getInstance().createStatus();
                status.setMessage("Connecting...");
                status.show();
                UITimer.timer(5000, false, () -> status.clear());
						
						
                        //Feedback f = new Feedback(tfUsername.getText(), tfPassword.getText()));
                        User u = new User(tfPassword.getText(), tfUsername.getText());
                        ArrayList<User> Users = ServiceUser.getInstance().FindUser(u);
                        if (Users != null) {
                            for (User usr  : Users) {
                                
                                UserId.set("UserId", usr.getId());
                                UserId.set("UserImg", usr.getImage());
                                UserId.set("UserRole", usr.getRole());
                                
                                String pw = usr.getPassword();

                                System.out.println("object === " + BCrypt.checkpw(tfPassword.getText(), pw));
                                if (BCrypt.checkpw(tfPassword.getText(), pw)) {

                                    Dialog.show("Success", "Connected", "OK", null);
                                    US = usr;
                                    HomeForm h = new HomeForm();
                                    h.getF().show();

                                } else {
                                    Dialog.show("ERROR", "Wrong Credentiels", "OK", null);

                                }
                            }

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

        add(myPic);
        add(tfUsername);
        add(tfPassword);
        add(btnSignIn);
        add(btnSignUp);

    }

}
