/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author LQss
 */
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.ServiceUser;
import com.mycompany.myapp.utils.BCrypt;
import com.codename1.io.Preferences;

/**
 *
 * @author bhk
 */
public class LoginForm extends Form {

    Form current;

    public LoginForm() {
        current = this;

        setTitle("Login");
        setLayout(BoxLayout.y());

        TextField tfUsername = new TextField("", "Username:");
        TextField tfPassword = new TextField("", "Password: ");

        Button btnSignIn = new Button("Sign In");
        Button btnSignUp = new Button("Sign Up");

        btnSignUp.addActionListener(e -> new SignUpForm(current).show());

        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfUsername.getText().length() == 0) || (tfPassword.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                } else {
                    try {
                        //Feedback f = new Feedback(tfUsername.getText(), tfPassword.getText()));
                        User u = new User(tfPassword.getText(), tfUsername.getText());

                        if (ServiceUser.getInstance().FindUser(u) != null) {
                            for (User usr : ServiceUser.getInstance().FindUser(u)) {

                                Preferences UserId = null;
                                UserId.delete("UserId");

                                UserId.set("UserId", usr.getId());

                                String pw = usr.getPassword();
                                System.out.println("object === " + BCrypt.checkpw(tfPassword.getText(), pw));
                                if (BCrypt.checkpw(tfPassword.getText(), pw)) {
                                    Dialog.show("Success", "Connected", new Command("Continue"));
                                    new HomeForm().show();

                                } else {
                                    Dialog.show("ERROR", "Wrong Credentiels", new Command("OK"));

                                }
                            }

                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        }
        );

        addAll(tfUsername, tfPassword, btnSignIn, btnSignUp);

    }

}
