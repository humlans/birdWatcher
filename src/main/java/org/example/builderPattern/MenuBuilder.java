package org.example.builderPattern;

import org.example.ProgramFacade;
import org.example.commandPattern.ICommand;
import org.example.databaseConnections.DatabaseConnection;
import org.example.databaseConnections.SightingConnection;
import org.example.items.BirdSpecies;
import org.example.items.Menu;
import org.example.items.Sighting;
import org.example.items.User;

import java.util.ArrayList;
import java.util.HashMap;

public class MenuBuilder {
    private User currentUser;
    private HashMap<String, ICommand> commands = new HashMap<>();
    private ArrayList<String> options = new ArrayList<>();
    private ProgramFacade program;
    private MenuDirector menuDirector = new MenuDirector();
    public MenuBuilder(ProgramFacade program) {
        this.program = program;
        currentUser = null;
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
        return new Menu(currentUser, commands, options);
    }

    public MenuBuilder setLoggedInMenu(User currentUser) {
        menuDirector.loggedInMenu(this, program, currentUser);
        return this;
    }

    public MenuBuilder setNotLoggedInMenu() {
        menuDirector.notLoggedInMenu(this, program);
        return this;
    }

    public MenuBuilder createSubscriptionMenu() {
        menuDirector.subscriptionMenu(this, program);
        return this;
    }

    public void emptyOptionsAndCommands() {
        options.clear();
        commands.clear();
    }
}
