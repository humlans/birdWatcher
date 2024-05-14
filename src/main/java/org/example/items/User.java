package org.example.items;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;
    private ArrayList<Sighting> sightings;

    public User(String username, String email, String password, boolean isAdmin, ArrayList<Sighting> sightings) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
        this.sightings = sightings;
    }

    public User() {
    }

    public boolean checkPassword(String gottenPassword) {
        if(gottenPassword.equals(password)){
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Sighting> getSightings() {
        return sightings;
    }

}
