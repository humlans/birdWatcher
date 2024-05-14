package org.example.handlers;

import org.example.items.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;

public class UserHandler {
    private HashMap<String, User> userList;

    private User currentUser = null;
    public UserHandler(HashMap<String, User> userList) {
        this.userList =userList;
    }

    public void login(String username, String password) {
        if(userList.containsKey(username)) {
            boolean loginSuccess = userList.get(username).checkPassword(password);
            if (loginSuccess) {
                currentUser = userList.get(username);
            }
        }
    }

    public void logout(JFrame currentFrame) {
        // Might want to do a save here as well but add that later...
        currentFrame.dispose();
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
