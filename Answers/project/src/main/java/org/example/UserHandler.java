package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserHandler {
    ArrayList<User> users = new ArrayList<>();
    public static UserHandler instance = null;

    private UserHandler(){}
    public static String savePath = null;
    public static UserHandler get(String path){
        if (instance == null){
            UserHandler.instance = new UserHandler();
            UserHandler.savePath = path;
        }
        return instance;
    }
    public boolean registerUser(String userName, String password, String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName))
                return false;
        }
        User newUser = new User();
        if (!newUser.setUserName(userName)){
            return false;
        }
        if (!newUser.setPassword(Util.hashPassword(password))){
            return false;
        }
        if (!newUser.setEmailaddress(email)){
            return false;
        }
        users.add(newUser);
        return true;
    }
    public boolean login(String userName, String password){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(userName)){
                if(users.get(i).getPassword().equals(Util.hashPassword(password))){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean saveUserData(){
        try {
            FileWriter usersData = new FileWriter(savePath);
            for (int i = 0; i < users.size(); i++){
                usersData.write(users.get(i).getUserName() + ",");
                usersData.write(Util.hashPassword(users.get(i).getPassword()) + ",");
                usersData.write(users.get(i).getEmailaddress() + "\n");
            }
            usersData.close();
        } catch (IOException e){
            return false;
        }
        return true;
    }
    public boolean loadUserData() {
        try {
            File userData = new File(savePath);
            Scanner reader = new Scanner(userData);
            while (reader.hasNext()){
                String data = reader.nextLine();
                String[] infos = data.split(",");
                registerUser(infos[0], infos[1], infos[2]);
            }
            reader.close();
            return true;
        } catch (FileNotFoundException e){
            return false;
        }
    }
}
