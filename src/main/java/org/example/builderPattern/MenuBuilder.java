package org.example.builderPattern;

import org.example.ProgramFacade;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.User;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuBuilder {

    private ArrayList<Sighting> allSightings = new ArrayList<>();

    private ArrayList<BirdSpecies> allBirdSpecies = new ArrayList<>();
    private User currentUser;
    private HashMap<String, ICommand> commands = new HashMap<>();
    private ArrayList<String> options = new ArrayList<>();
    private ProgramFacade program;
    private MenuDirector menuDirector = new MenuDirector();
    public MenuBuilder(ProgramFacade program) {
        this.program = program;
        currentUser = null;
    }

    public MenuBuilder setAllSightings() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        allSightings = databaseConnection.getAllSightings();
        return this;
    }


    public MenuBuilder setAllBirdSpecies() {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        allBirdSpecies = databaseConnection.getAllBirdSpecies();
        return this;
    }
    public MenuBuilder setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        return this;
    }
    public MenuBuilder setCommands(String key, ICommand command) {
        commands.put(key, command);
        return this;
    }
    public MenuBuilder setOptions(String option) {
        options.add(option);
        return this;
    }



    public Menu build() {
        return new Menu(allSightings, currentUser, allBirdSpecies, commands, options);
    }

    public MenuBuilder setLoggedInMenu(User currentUser) {
        menuDirector.loggedInMenu(this, program, currentUser);
        return this;
    }

    public MenuBuilder setNotLoggedInMenu() {
        menuDirector.notLoggedInMenu(this, program);
        return this;
    }

    public void emptyOptionsAndCommands() {
        options.clear();
        commands.clear();
    }
}
