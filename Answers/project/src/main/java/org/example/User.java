package org.example;

import javax.swing.plaf.IconUIResource;

public class User {
    private String userName;
    private String password;
    private String emailaddress;
    // **** Getters & Setters **** //
    public String getUserName() {
        return userName;
    }
    public boolean setUserName(String userName) {
        if (userName.matches("^[a-zA-Z0-9_]{4,29}$")) {
            this.userName = userName;
            return true;
        }
        return false;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        this.password = password;
        return true;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public boolean setEmailaddress(String emailaddress) {
        if (Util.emailRegex(emailaddress)) {
            this.emailaddress = emailaddress;
            return true;
        }
        return false;
    }
}
