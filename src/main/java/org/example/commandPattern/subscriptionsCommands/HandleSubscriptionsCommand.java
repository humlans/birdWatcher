package org.example.commandPattern.subscriptionsCommands;

import org.example.ProgramFacade;
import org.example.builderPattern.MenuBuilder;
import org.example.builderPattern.MenuDirector;
import org.example.commandPattern.ICommand;
import org.example.items.Menu;

import java.util.ArrayList;
import java.util.HashMap;

public class HandleSubscriptionsCommand implements ICommand {

    ArrayList<String> options;
    HashMap<String, ICommand> commands;

    private ProgramFacade program;
    private Menu menu;
    public HandleSubscriptionsCommand(ProgramFacade program) {
        //Creates a menu to handle subscriptions.
        this.program = program;
        MenuBuilder menuBuilder = new MenuBuilder(program);
        menu = menuBuilder.createSubscriptionMenu().build();
    }

    @Override
    public void execute() {
        //Gives the user a choice on what to with their subscriptions.
        menu.printMenuOptionsAndExecute();
    }
}
