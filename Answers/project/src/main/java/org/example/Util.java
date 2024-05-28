package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Util {
    public static boolean isPasswordStrong(String password){
        final char SPECIAL[] = {'#', '@', '$', '!', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '~', '`', ',', '.', '<', '>', '?', '/', '\\', '[', ']', '{', '}', '|', ':', ';'};
        int level = 0;
        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        if (password.length() < 8){
            return false;
        }
        for (int i = 0; i < password.length(); i++){
            if (password.charAt(i) == ' '){
                return false;
            }
            if (Character.isLowerCase(password.charAt(i))){
                hasLower = true;
                continue;
            }
            if (Character.isUpperCase(password.charAt(i))){
                hasUpper = true;
                continue;
            }
            if (Character.isDigit(password.charAt(i))){
                hasNumber = true;
                continue;
            }
            for (char c : SPECIAL ){
                if (c == password.charAt(i)){
                    hasSpecial = true;
                    break;
                }
            }
        }
        if(password.length() > 8){
            level++;
        }
        if(hasLower || hasUpper || hasNumber){
            level++;
        }
        if(hasLower && hasUpper && hasNumber){
            level++;
        }
        if(hasSpecial){
            level++;
        }
        return level >= 3;
    }
    public static boolean emailRegex(String email){
        final String EMAIL_PATTERN =
                "^[\\w!#$%&amp;'*+/=?{|}~^-]+(?:\\.[\\w!#$%&amp;'*+/=?{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        return email.matches(EMAIL_PATTERN);
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
