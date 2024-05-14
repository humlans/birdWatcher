package org.example.builderPattern;

import org.example.ProgramFacade;
import org.example.commandPattern.*;
import org.example.items.Menu;
import org.example.items.User;

public class MenuDirector {

    public MenuDirector() {
    }

    public void notLoggedInMenu(MenuBuilder menuBuilder, ProgramFacade program) {
        Menu menu = program.getMenu();
        menuBuilder.setOptions("1: Login.")
                .setOptions("2: See sighting.")
                .setOptions("3: See bird species.")
                .setOptions("4: Quit")
                .setCommands("1", new LoginCommand(program))
                .setCommands("2", new SeeSightingCommand(program))
                .setCommands("3", new SeeBirdSpeciesCommand(program))
                .setCommands("4", new QuitCommand(program))
                .setAllSightings()
                .setAllBirdSpecies();
    }

    public void loggedInMenu(MenuBuilder menuBuilder, ProgramFacade program, User currentUser) {
        Menu menu = program.getMenu();
        menuBuilder.setOptions("1: Logout.")
                .setOptions("2: See sighting.")
                .setOptions("3: See bird species.")
                .setOptions("4: Add new sighting.")
                .setOptions("5: Add new bird species.")
                .setOptions("6: Edit sighting.")
                .setOptions("7: Edit bird species.")
                .setOptions("8: Delete sighting or bird species.")
                .setOptions("9: Quit")
                .setCommands("1", new LogoutCommand(program))
                .setCommands("2", new SeeSightingCommand(program))
                .setCommands("3", new SeeBirdSpeciesCommand(program))
                .setCommands("4", new CreateSightingCommand(program))
                .setCommands("5", new CreateNewSpeciesCommand(menu))
                .setCommands("6", new EditSightingCommand(program))
                .setCommands("7", new EditBirdSpeciesCommand(program))
                .setCommands("8", new DeleteCommand(program))
                .setCommands("9", new QuitCommand(program))
                .setAllSightings()
                .setAllBirdSpecies()
                .setCurrentUser(currentUser);
    }
}
