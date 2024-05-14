package org.example.items;

import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.Sighting;
import org.example.items.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Menu {
    private ArrayList<Sighting> allSightings = new ArrayList<>();
    private ArrayList<BirdSpecies> allBirdSpecies = new ArrayList<>();
    private User currentUser;
    private HashMap<String, ICommand> commands = new HashMap<>();
    private ArrayList<String> options = new ArrayList<>();

    public Menu(ArrayList<Sighting> allSightings, User currentUser, ArrayList<BirdSpecies> allBirdSpecies, HashMap<String, ICommand> commands, ArrayList<String> options) {
        this.allSightings = allSightings;
        this.currentUser = currentUser;
        this.allBirdSpecies = allBirdSpecies;
        this.commands = commands;
        this.options = options;
    }

    public Menu() {
    }

    public void printMenuOptionsAndExecute() {
        //Unsure if I should keep this or not, just to always have updated arraylists when getting back to the menu
        DatabaseConnection databaseConnection = new DatabaseConnection();
        allSightings = databaseConnection.getAllSightings();
        allBirdSpecies = databaseConnection.getAllBirdSpecies();

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

    public ArrayList<BirdSpecies> getBirdSpecies() {
        return allBirdSpecies;
    }

    public ArrayList<Sighting> getAllSightings() {
        return allSightings;
    }

    public void addBirdSpecies(BirdSpecies newBirdSpecies) {
        allBirdSpecies.add(newBirdSpecies);
    }
    public void addSighting(Sighting sighting) {
        allSightings.add(sighting);
    }
}
