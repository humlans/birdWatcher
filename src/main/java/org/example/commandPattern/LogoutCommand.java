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
        MenuBuilder menuBuilder = new MenuBuilder(program);
        menuBuilder.emptyOptionsAndCommands();
        Menu newLoggedInMenu = menuBuilder.setNotLoggedInMenu().build();
        newLoggedInMenu.setCurrentUser(null);
        program.setMenu(newLoggedInMenu);

    }
}
