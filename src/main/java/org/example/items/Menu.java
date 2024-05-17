package org.example.items;

import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.SightingConnection;
import org.example.items.Sighting;
import org.example.items.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    private User currentUser;
    private HashMap<String, ICommand> commands = new HashMap<>();
    private ArrayList<String> options = new ArrayList<>();

    public Menu(User currentUser, HashMap<String, ICommand> commands, ArrayList<String> options) {
        this.currentUser = currentUser;
        this.commands = commands;
        this.options = options;
    }

    public Menu() {
    }

    public void printMenuOptionsAndExecute() {
        UserInput userInput = new UserInput();
        if(currentUser != null) {
            System.out.println("\nCurrent user: " + currentUser.getUsername());
        }
        System.out.println();
        for (String option : options) {
            System.out.println(option);
        }
        System.out.println("Choose an option: ");
        String input = userInput.getCanBeBlankStringInput();
        if (commands.containsKey(input)) {
            commands.get(input).execute();
            return;
        }
        System.out.println("Please choose one of the options");
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }


}
