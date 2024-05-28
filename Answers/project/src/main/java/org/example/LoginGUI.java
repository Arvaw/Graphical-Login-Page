package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class LoginGUI {

    private final int width = 550;
    private final int height = 800;
    Frame entry = null;
    Frame login = null;
    Frame signup = null;
    private static LoginGUI inctance = null;
    public static LoginGUI get(){
        if (inctance == null){
            LoginGUI.inctance = new LoginGUI();
        }
        return inctance;
    }
    public void init(){

        // User Handler
        UserHandler users = UserHandler.get("users.txt");

        users.loadUserData();

        entry = new JFrame("♥Login Project♥");
        login = new JFrame("LogIn");
        signup = new JFrame("SignUp");

        entry.setVisible(true);
        login.setVisible(false);
        signup.setVisible(false);

        login.setSize(width, height);
        signup.setSize(width, height);
        entry.setSize(width, height);

        entry.setResizable(false);
        login.setResizable(false);
        signup.setResizable(false);

        entry.setLayout(null);
        login.setLayout(null);
        // entry
        entry.setBackground(Color.BLUE);
        Button loginbtn = new Button("LogIn");
        Button signupbtn = new Button("SignUp");
        Label welcome = new Label("**Welcome**");
        welcome.setFont(new Font("Serif", Font.PLAIN, 50));
        welcome.setBounds(((width / 2) - (350 / 2) +30), 225, 350, 50);
        loginbtn.setBounds(width / 2 - 100, height / 2 - 50 -50, 200, 100);
        signupbtn.setBounds(width / 2 - 100, height / 2 + 50 -50, 200, 100);
        loginbtn.setBackground(new Color(0xFFB5DA));
        signupbtn.setBackground(new Color(0xFF7ED4));

        // login
        Button loginSubmit = new Button("Submit");
        loginSubmit.setBackground(new Color(0xFF3EA5));
        loginSubmit.setBounds(width / 2 - 100, height / 2, 200, 100);

        TextField usernameFeild = new TextField();
        usernameFeild.setText("UserName");
        TextField passwordFeild = new TextField();
        passwordFeild.setText("PassWord");

        usernameFeild.setBounds(width / 2 - 100, height / 2 - 100, 200, 50);
        passwordFeild.setBounds(width / 2 - 100, height / 2 - 50, 200, 50);


        // sign up
        signup.setLayout(null);
        TextField emailFeild = new TextField();
        emailFeild.setText("Email");
        emailFeild.setBounds(width / 2 - 100, height / 2 - 150, 200, 50);
        Button singUpSubmit = new Button("Submit");
        singUpSubmit.setBackground(new Color(0xFF3EA5));
        singUpSubmit.setBounds(width / 2 - 100, height / 2, 200, 100);
        TextField usernameFeild1 = new TextField();
        usernameFeild1.setText("UserName");
        TextField passwordFeild1 = new TextField();
        passwordFeild1.setText("PassWord");
        usernameFeild1.setBounds(width / 2 - 100, height / 2 - 100, 200, 50);
        passwordFeild1.setBounds(width / 2 - 100, height / 2 - 50, 200, 50);
        signup.add(singUpSubmit);
        signup.add(usernameFeild1);
        signup.add(passwordFeild1);
        signup.add(emailFeild);

        login.add(loginSubmit);
        login.add(usernameFeild);
        login.add(passwordFeild);

        entry.add(loginbtn);
        entry.add(signupbtn);
        entry.add(welcome);

        // action listners
        loginbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entry.setVisible(false);
                login.setVisible(true);
            }
        });

        signupbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                entry.setVisible(false);
                signup.setVisible(true);
            }
        });

        loginSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if (users.login(usernameFeild.getText(), passwordFeild.getText())) {
                        JOptionPane.showMessageDialog(login, "Welcome to the System!");
                        JOptionPane.showMessageDialog(login, "LogIn Successful!");
                        login.setVisible(false);
                        entry.setVisible(true);
                    }
                    else {
                        JOptionPane.showMessageDialog(login, "There is problem logging in!");
                        login.setVisible(false);
                        entry.setVisible(true);
                    }
            }
        });

        singUpSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(users.registerUser(usernameFeild1.getText(), passwordFeild1.getText(), emailFeild.getText())) {
                    JOptionPane.showMessageDialog(signup, "You Can LogIn now!");
                    JOptionPane.showMessageDialog(signup, "SignUp Successful!");
                    signup.setVisible(false);
                    entry.setVisible(true);
                    users.saveUserData();
                }
                else {
                    JOptionPane.showMessageDialog(signup, "There is a problem signing in!");
                    signup.setVisible(false);
                    entry.setVisible(true);
                }
            }
        });
    }
}
