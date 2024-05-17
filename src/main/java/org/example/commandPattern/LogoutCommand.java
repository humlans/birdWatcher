package org.example.commandPattern;

import org.example.ProgramFacade;
import org.example.builderPattern.MenuBuilder;
import org.example.items.Menu;

public class LogoutCommand implements ICommand{
    private ProgramFacade program;

    public LogoutCommand(ProgramFacade program) {
        this.program = program;
    }

    @Override
    public void execute() {
        //Creates a new not logged in menu.
        MenuBuilder menuBuilder = new MenuBuilder(program);
        menuBuilder.emptyOptionsAndCommands();
        Menu newLoggedInMenu = menuBuilder.setNotLoggedInMenu().build();

        //Empties the current user.
        newLoggedInMenu.setCurrentUser(null);

        //Switches the program to the new menu
        program.setMenu(newLoggedInMenu);
    }
}
