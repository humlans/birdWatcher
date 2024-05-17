package org.example.builderPattern;

import org.example.ProgramFacade;
import org.example.commandPattern.*;
import org.example.commandPattern.birdSpeciesCommands.CreateNewSpeciesCommand;
import org.example.commandPattern.birdSpeciesCommands.EditBirdSpeciesCommand;
import org.example.commandPattern.birdSpeciesCommands.SeeBirdSpeciesCommand;
import org.example.commandPattern.sightingCommands.CreateSightingCommand;
import org.example.commandPattern.sightingCommands.EditSightingCommand;
import org.example.commandPattern.sightingCommands.SeeSightingCommand;
import org.example.commandPattern.subscriptionsCommands.*;
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
                .setCommands("2", new SeeSightingCommand())
                .setCommands("3", new SeeBirdSpeciesCommand())
                .setCommands("4", new QuitCommand(program));
    }

    public void loggedInMenu(MenuBuilder menuBuilder, ProgramFacade program, User currentUser) {
        Menu menu = program.getMenu();
        menuBuilder.setOptions("1: Logout.")
                .setOptions("2. See notifications")
                .setOptions("3: See sighting.")
                .setOptions("4: See bird species.")
                .setOptions("5: Add new sighting.")
                .setOptions("6: Add new bird species.")
                .setOptions("7: Edit sighting.")
                .setOptions("8: Edit bird species.")
                .setOptions("9: Delete sighting or bird species.")
                .setOptions("10. Handle subscriptions.")
                .setOptions("11: Quit")
                .setCommands("1", new LogoutCommand(program))
                .setCommands("2", new SeeNotificationsCommand(program))
                .setCommands("3", new SeeSightingCommand())
                .setCommands("4", new SeeBirdSpeciesCommand())
                .setCommands("5", new CreateSightingCommand(program))
                .setCommands("6", new CreateNewSpeciesCommand(menu))
                .setCommands("7", new EditSightingCommand(program))
                .setCommands("8", new EditBirdSpeciesCommand(program))
                .setCommands("9", new DeleteCommand(program))
                .setCommands("10", new HandleSubscriptionsCommand(program))
                .setCommands("11", new QuitCommand(program))
                .setCurrentUser(currentUser);
    }

    public void subscriptionMenu(MenuBuilder menuBuilder, ProgramFacade program) {
        menuBuilder.setOptions("1. Add new subscription")
                .setOptions("2. Delete subscription")
                .setOptions("3. See subscriptions")
                .setCommands("1", new AddSubscriptionsCommand(program))
                .setCommands("2", new DeleteSubscriptionCommand(program))
                .setCommands("3", new SeeSubscriptionsCommand(program));
    }
}
